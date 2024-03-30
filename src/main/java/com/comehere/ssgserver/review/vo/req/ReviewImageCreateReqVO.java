package com.comehere.ssgserver.review.vo.req;

import lombok.Getter;

@Getter
public class ReviewImageCreateReqVO {
	private Long reviewId;

	private String imageUrl;

	private String alt;
}
