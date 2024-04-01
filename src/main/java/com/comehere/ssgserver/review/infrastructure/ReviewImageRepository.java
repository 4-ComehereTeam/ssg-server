package com.comehere.ssgserver.review.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.comehere.ssgserver.review.domain.Review;
import com.comehere.ssgserver.review.domain.ReviewImage;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
	List<ReviewImage> findByReview(Review review);

	List<ReviewImage> findByReviewId(Long reviewId);
}
