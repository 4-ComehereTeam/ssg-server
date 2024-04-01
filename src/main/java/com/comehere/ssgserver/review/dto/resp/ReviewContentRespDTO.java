package com.comehere.ssgserver.review.dto.resp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.comehere.ssgserver.review.domain.Review;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewContentRespDTO {
	private Long reviewId;

	private String signinId;

	private String content;

	private Short star;

	private LocalDateTime createAt;

	public static ReviewContentRespDTO toBuild(Review review) {
		return ReviewContentRespDTO.builder()
				.content(review.getContent())
				.reviewId(review.getId())
				.createAt(review.getCreateAt())
				.signinId(review.getSigninId())
				.star(review.getStar())
				.build();
	}
}
