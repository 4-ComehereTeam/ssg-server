package com.comehere.ssgserver.review.dto.resp;

import lombok.Getter;

@Getter
public class ReviewImageDTO {
	private Long reviewId;

	private Long imageId;

	private String url;

	private String alt;
}
