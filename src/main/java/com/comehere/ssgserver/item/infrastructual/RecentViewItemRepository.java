package com.comehere.ssgserver.item.infrastructual;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.item.domain.RecentViewItem;

public interface RecentViewItemRepository extends JpaRepository<RecentViewItem, Long> {
	Optional<RecentViewItem> findByItemIdAndUuid(Long itemId, UUID uuid);
}
