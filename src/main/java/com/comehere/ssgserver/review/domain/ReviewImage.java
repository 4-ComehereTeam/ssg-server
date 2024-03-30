package com.comehere.ssgserver.review.domain;

import com.comehere.ssgserver.review.domain.Review;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class ReviewImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "review_id", nullable = false, updatable = false)
	private Review review;

	@Column(nullable = false)
	private String imageUrl;

	@Column(nullable = false)
	private String alt;

	@Builder
	public ReviewImage(Long id, Review review, String imageUrl, String alt) {
		this.id = id;
		this.review = review;
		this.imageUrl = imageUrl;
		this.alt = alt;
	}
}
