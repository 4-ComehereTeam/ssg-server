package com.comehere.ssgserver.bundle.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.bundle.domain.BundleWithItem;

public interface BundleWithItemRepository extends JpaRepository<BundleWithItem, Long> {
}
