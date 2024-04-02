package com.comehere.ssgserver.item.infrastructual;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.comehere.ssgserver.item.domain.RecentViewItem;

public interface RecentViewItemRepository extends JpaRepository<RecentViewItem, Long> {
	Optional<RecentViewItem> findByItemIdAndUuid(Long itemId, UUID uuid);

	Slice<RecentViewItem> findByUuid(UUID uuid, Pageable page);

	@Modifying(clearAutomatically = true)
	@Query("delete from RecentViewItem rvi where rvi.uuid = :uuid and rvi.id in :recentIds")
	void deleteByUuidAndIds(UUID uuid, List<Long> recentIds);
}
