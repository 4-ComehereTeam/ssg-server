package com.comehere.ssgserver.clip.application;

import java.util.UUID;

import com.comehere.ssgserver.clip.dto.req.ItemsClipDeleteReqDTO;
import com.comehere.ssgserver.clip.dto.req.ItemsClipGetRespDTO;

public interface ItemClipService {
	void createItemClip(UUID uuid, Long itemId);

	void deleteItemClip(UUID uuid, Long itemId);

	void deleteItemsClip(UUID uuid, ItemsClipDeleteReqDTO vo);

	ItemsClipGetRespDTO getItemsClip(UUID uuid);
}
