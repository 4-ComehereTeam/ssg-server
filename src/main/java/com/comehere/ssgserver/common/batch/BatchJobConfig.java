package com.comehere.ssgserver.common.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.item.domain.ItemCalc;
import com.comehere.ssgserver.review.dto.resp.ReviewSummaryDTO;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
@Transactional
public class BatchJobConfig {
	// private final JobRepository jobRepository;
	private final EntityManagerFactory emf;

	@Bean(name = "itemSummaryJob")
	public Job itemSummaryJob(JobRepository jobRepository, PlatformTransactionManager txm) throws Exception {
		return new JobBuilder("itemSummaryJob", jobRepository)
				.start(step(jobRepository, txm))
				.build();
	}

	@Bean(name = "itemSummaryStep")
	@JobScope
	public Step step(JobRepository jobRepository, PlatformTransactionManager txm) throws Exception {
		return new StepBuilder("itemSummaryStep", jobRepository)
				.<ReviewSummaryDTO, ItemCalc>chunk(10, txm)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.allowStartIfComplete(true)
				.build();
	}

	@Bean
	@StepScope
	public JpaPagingItemReader<ReviewSummaryDTO> reader() throws Exception {

		return new JpaPagingItemReaderBuilder<ReviewSummaryDTO>()
				.pageSize(100)
				.queryString(
						"select new com.comehere.ssgserver.review.dto.resp.ReviewSummaryDTO("
								+ "min(i.id), min(ic.id), round(avg(r.star), 1), count(r)) "
								+ "from Review r "
								+ "join Item i on r.itemCode = i.code "
								+ "join ItemCalc ic on i.id = ic.itemId "
								+ "group by r.itemCode"
				)
				.entityManagerFactory(emf)
				.name("itemSummaryReader")
				.build();
	}

	@Bean
	@StepScope
	public ItemProcessor<ReviewSummaryDTO, ItemCalc> processor() {
		return new ItemProcessor<ReviewSummaryDTO, ItemCalc>() {
			@Override
			public ItemCalc process(ReviewSummaryDTO dto) throws Exception {
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
