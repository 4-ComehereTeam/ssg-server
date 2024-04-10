package com.comehere.ssgserver.review.infrastructure;

import static com.comehere.ssgserver.item.domain.QItem.*;
import static com.comehere.ssgserver.item.domain.QItemCalc.*;
import static com.comehere.ssgserver.review.domain.QReview.*;
import static com.comehere.ssgserver.review.domain.QReviewImage.*;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import com.comehere.ssgserver.item.domain.QItem;
import com.comehere.ssgserver.review.domain.QReview;
import com.comehere.ssgserver.review.dto.resp.ReviewImageDTO;
import com.comehere.ssgserver.review.dto.resp.ReviewImageListDTO;
import com.comehere.ssgserver.review.dto.resp.ReviewSummaryDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomReviewRepositoryImpl implements CustomReviewRepository {
	private final JPAQueryFactory query;

	@Override
	public Slice<ReviewImageListDTO> getReviewImageList(String itemCode, Pageable page) {
		List<ReviewImageListDTO> result = query
				.select(Projections.constructor(ReviewImageListDTO.class,
						reviewImage.review.id.as("reviewId"),
						reviewImage.id.as("imageId"),
						reviewImage.imageUrl.as("url"),
						reviewImage.alt))
				.from(reviewImage)
				.join(reviewImage.review, review)
				.where(review.itemCode.eq(itemCode))
				.limit(page.getPageSize() + 1)
				.offset(page.getOffset())
				.fetch();

		boolean hasNext = false;
		if(result.size() > page.getPageSize()) {
			result.remove(page.getPageSize());
			hasNext = true;
		}

		return new SliceImpl<>(result, page, hasNext);
	}

	@Override
	public List<ReviewSummaryDTO> getReviewSummary() {
		return query
				.select(Projections.constructor(ReviewSummaryDTO.class,
						item.id.min().as("itemId"),
						itemCalc.id.min().as("calcId"),
						review.star.avg().as("averageStar"),
						review.count().as("reviewCount")
				))
				.from(review)
				.join(item).on(review.itemCode.eq(item.code))
				.join(itemCalc).on(item.id.eq(itemCalc.itemId))

				.groupBy(review.itemCode)
				.fetch();
	}
}
