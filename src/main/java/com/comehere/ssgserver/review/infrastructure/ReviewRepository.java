package com.comehere.ssgserver.review.infrastructure;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.review.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	Boolean existsByPurchaseListId(Long purchaseListId);

	Optional<Review> findByUuidAndId(UUID uuid, Long id);
}
