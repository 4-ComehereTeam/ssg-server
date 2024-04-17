package com.comehere.ssgserver.review.vo.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ReviewImageUpdateReqVO {
	private Long reviewImageId;

	private String imageUrl;

	private String alt;
}
