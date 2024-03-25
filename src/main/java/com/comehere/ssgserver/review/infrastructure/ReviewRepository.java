package com.comehere.ssgserver.review.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.review.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	Optional<Review> findByPurchaseListId(Long purchaseListId);
}
