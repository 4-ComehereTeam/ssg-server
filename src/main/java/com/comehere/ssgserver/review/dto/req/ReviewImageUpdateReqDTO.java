package com.comehere.ssgserver.review.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewImageUpdateReqDTO {
	private Long reviewImageId;

	private String imageUrl;

	private String alt;
}
