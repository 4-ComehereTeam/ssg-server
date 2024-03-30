package com.comehere.ssgserver.review.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.review.domain.Review;
import com.comehere.ssgserver.review.domain.ReviewImage;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
	List<ReviewImage> findByReview(Review review);
}
