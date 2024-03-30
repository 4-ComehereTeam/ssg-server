package com.comehere.ssgserver.review.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewUpdateReqDTO {
	private Long reviewId;

	private Short star;

	private String content;
}
