package com.comehere.ssgserver.review.dto.resp;

import com.comehere.ssgserver.review.domain.ReviewImage;

import lombok.Getter;

@Getter
public class ReviewImageDTO {
	private Long imageId;

	private String url;

	private String alt;

	public ReviewImageDTO(ReviewImage reviewImage) {
		this.imageId = reviewImage.getId();
		this.url = reviewImage.getImageUrl();
		this.alt = reviewImage.getAlt();
	}
}
