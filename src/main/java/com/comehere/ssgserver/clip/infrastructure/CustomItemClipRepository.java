package com.comehere.ssgserver.clip.infrastructure;

import java.util.UUID;

public interface CustomItemClipRepository {
	void deleteItemClip(UUID uuid, Long itemId);
}
