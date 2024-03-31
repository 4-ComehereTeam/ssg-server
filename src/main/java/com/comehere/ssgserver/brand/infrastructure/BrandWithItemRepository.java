package com.comehere.ssgserver.brand.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comehere.ssgserver.brand.domain.BrandWithItem;

public interface BrandWithItemRepository extends JpaRepository<BrandWithItem, Long> {
	@Query("select bwi from BrandWithItem bwi join fetch bwi.brand b where bwi.item.id = :itemId")
	Optional<BrandWithItem> findByItemId(@Param("itemId") Long itemId);
}
