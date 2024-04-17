package com.comehere.ssgserver.bundle.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.w3c.dom.stylesheets.LinkStyle;

import com.comehere.ssgserver.bundle.domain.BundleWithItem;

public interface BundleWithItemRepository extends JpaRepository<BundleWithItem, Long> {
	@Query("select bwi.item.id from BundleWithItem bwi where bwi.bundle.id = :bundleId")
	List<Long> findByBundleId(@Param("bundleId") Long bundleId);
}
