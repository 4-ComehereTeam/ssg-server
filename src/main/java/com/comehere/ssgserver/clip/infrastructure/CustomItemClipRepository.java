package com.comehere.ssgserver.clip.infrastructure;

import java.util.List;
import java.util.UUID;

public interface CustomItemClipRepository {
	void deleteItemClip(UUID uuid, Long itemId);

	List<Long> findItemIdsByUuid(UUID uuid);
}
