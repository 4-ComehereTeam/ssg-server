package com.comehere.ssgserver.image.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.image.domain.ReviewImage;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
	List<ReviewImage> findByReviewId(Long reviewId);
}
