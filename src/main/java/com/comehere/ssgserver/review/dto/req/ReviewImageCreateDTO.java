package com.comehere.ssgserver.review.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewImageCreateDTO {
	private Long reviewId;

	private String imageUrl;

	private String alt;
}
