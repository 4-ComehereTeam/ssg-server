package com.comehere.ssgserver.common.batch;

import static com.comehere.ssgserver.item.domain.QItem.*;
import static com.comehere.ssgserver.item.domain.QItemCalc.*;
import static com.comehere.ssgserver.review.domain.QReview.*;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.item.domain.ItemCalc;
import com.comehere.ssgserver.review.dto.resp.ReviewSummaryDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Configuration
@RequiredArgsConstructor
@Transactional
public class BatchJobConfig {
	private final JobRepository jobRepository;
	private final JPAQueryFactory query;
	private final EntityManagerFactory emf;

	@Bean
	public Job itemSummaryJob(PlatformTransactionManager txm) throws Exception {
		return new JobBuilder("itemSummaryJob", jobRepository)
				.start(step(txm))
				.build();
	}

	@Bean
	@JobScope
	public Step step(PlatformTransactionManager txm) throws Exception {
		return new StepBuilder("itemSummaryStep", jobRepository)
				.<ReviewSummaryDTO, ItemCalc>chunk(100, txm)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.allowStartIfComplete(true)
				.build();
	}

	@Bean
	@StepScope
	public QuerydslPagingItemReader<ReviewSummaryDTO> reader() throws Exception {

		return new QuerydslPagingItemReader<>(query, 100, query -> query
				.select(Projections.constructor(ReviewSummaryDTO.class,
					item.id.min().as("itemId"),
					itemCalc.id.min().as("calcId"),
					review.star.avg().as("averageStar"),
					review.count().as("reviewCount")
			))
			.from(review)
			.join(item).on(review.itemCode.eq(item.code))
			.join(itemCalc).on(item.id.eq(itemCalc.itemId))

			.groupBy(review.itemCode));
	}

	@Bean
	@StepScope
	public ItemProcessor<ReviewSummaryDTO,ItemCalc> processor() {
		// return new ItemProcessor<ReviewSummaryDTO, ItemCalc>() {
		// 	@Override
		// 	public ItemCalc process(ReviewSummaryDTO dto) throws Exception {
		// 		return ItemCalc.builder()
		// 				.id(dto.getCalcId())
		// 				.reviewCount(dto.getReviewCount())
		// 				.averageStar(dto.getAverageStar())
		// 				.build();
		// 	}
		// };

		log.info("processor initialized");
		return new ItemProcessor<ReviewSummaryDTO, ItemCalc>() {
			private int processCount = 0;

			@Override
			public ItemCalc process(ReviewSummaryDTO dto) throws Exception {
				processCount++;
				log.info("Processing item #{}", processCount);
				return ItemCalc.builder()
						.id(dto.getCalcId())
						.reviewCount(dto.getReviewCount())
						.averageStar(dto.getAverageStar())
						.build();
			}
		};
	}

	@Bean
	@StepScope
	public JpaItemWriter<ItemCalc> writer() {
		return new JpaItemWriterBuilder<ItemCalc>()
				.entityManagerFactory(emf)
				.build();
	}
}
