package com.comehere.ssgserver.clip.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.comehere.ssgserver.clip.domain.ItemClip;

public interface ItemClipRepository extends JpaRepository<ItemClip, Long> {
	boolean existsByUuidAndItemId(UUID uuid, Long itemId);

	Optional<ItemClip> findByUuidAndItemId(UUID uuid, Long itemId);

	@Modifying
	@Query("delete from ItemClip ic where ic.uuid = :uuid and ic.itemId in :itemIds")
	void deleteByUuidAndByIds(UUID uuid, List<Long> itemIds);

	List<ItemClip> findByUuid(UUID uuid);
}
