package com.comehere.ssgserver.image.domain;

import com.comehere.ssgserver.image.vo.ReviewImageVO;
import com.comehere.ssgserver.review.domain.Review;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReviewImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "review_id")
	private Review review;

	@Column(nullable = false)
	private String imageUrl;

	private String alt;

	@Builder
	public ReviewImage(Review review, String imageUrl, String alt) {
		this.review = review;
		this.imageUrl = imageUrl;
		this.alt = alt;
	}

	public void updateReviewImage(ReviewImageVO reviewImageVO) {
		this.imageUrl = reviewImageVO.getImageUrl();
		this.alt = reviewImageVO.getAlt();
	}
}
