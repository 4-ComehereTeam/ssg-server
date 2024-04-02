package com.comehere.ssgserver.review.infrastructure;

import static com.comehere.ssgserver.review.domain.QReview.*;
import static com.comehere.ssgserver.review.domain.QReviewImage.*;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import com.comehere.ssgserver.review.dto.resp.ReviewImageDTO;
import com.comehere.ssgserver.review.dto.resp.ReviewImageListDTO;
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
}
