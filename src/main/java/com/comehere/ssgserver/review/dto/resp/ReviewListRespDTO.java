package com.comehere.ssgserver.review.dto.resp;

import java.util.List;

import org.springframework.data.domain.Slice;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewListRespDTO {
	private List<Long> reviews;

	private Integer currentPage;

	private Boolean hasNext;

	public static ReviewListRespDTO toBuild(Slice<Long> reviews) {
		return ReviewListRespDTO.builder()
				.reviews(reviews.getContent())
				.currentPage(reviews.getNumber())
				.hasNext(reviews.hasNext())
				.build();
	}
}
