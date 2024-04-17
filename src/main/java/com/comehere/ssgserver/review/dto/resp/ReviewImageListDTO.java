package com.comehere.ssgserver.review.dto.resp;

import com.comehere.ssgserver.review.domain.ReviewImage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewImageListDTO {
	private Long reviewId;

	private Long imageId;

	private String url;

	private String alt;

	public ReviewImageListDTO(ReviewImage reviewImage) {
		this.reviewId = reviewImage.getReview().getId();
		this.imageId = reviewImage.getId();
		this.url = reviewImage.getImageUrl();
		this.alt = reviewImage.getAlt();
	}
}
