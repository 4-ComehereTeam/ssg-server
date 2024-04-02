package com.comehere.ssgserver.review.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.comehere.ssgserver.review.domain.Review;
import com.comehere.ssgserver.review.domain.ReviewImage;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	Boolean existsByPurchaseListId(Long purchaseListId);

	Optional<Review> findByUuidAndId(UUID uuid, Long id);

	@Query("select r.id from Review r where r.itemCode = :itemCode")
	Slice<Long> findByItemCode(String itemCode, Pageable page);

	@Query("select r.id from Review r where r.uuid = :uuid")
	Slice<Long> findByUuid(UUID uuid, Pageable page);
}
