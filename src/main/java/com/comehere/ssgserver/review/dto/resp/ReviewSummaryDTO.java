package com.comehere.ssgserver.review.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewSummaryDTO {
	private Long itemId;

	private Long calcId;

	private Double averageStar;

	private Long reviewCount;
}
