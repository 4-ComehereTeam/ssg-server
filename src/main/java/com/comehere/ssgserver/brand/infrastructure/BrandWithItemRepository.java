package com.comehere.ssgserver.brand.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comehere.ssgserver.brand.domain.BrandWithItem;

public interface BrandWithItemRepository extends JpaRepository<BrandWithItem, Long> {
	@Query("select bwi from BrandWithItem bwi join fetch bwi.brand b where bwi.item.id = :itemId")
	Optional<BrandWithItem> findByItemId(@Param("itemId") Long itemId);

	@Query("select bwi.item.id from BrandWithItem bwi where bwi.brand.id = :brandId")
	Slice<Long> findByBrandId(@Param("brandId") Long brandId, Pageable page);
}
