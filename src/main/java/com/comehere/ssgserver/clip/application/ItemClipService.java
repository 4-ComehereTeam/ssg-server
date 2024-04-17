package com.comehere.ssgserver.clip.application;

import java.util.UUID;

import com.comehere.ssgserver.clip.dto.req.ItemsClipDeleteReqDTO;
import com.comehere.ssgserver.clip.dto.resp.ItemsClipGetRespDTO;
import com.comehere.ssgserver.clip.dto.resp.ItemClipGetRespDTO;

public interface ItemClipService {
	void createItemClip(UUID uuid, Long itemId);

	void deleteItemClip(UUID uuid, Long itemId);

	void deleteItemsClip(UUID uuid, ItemsClipDeleteReqDTO vo);

	ItemsClipGetRespDTO getItemsClip(UUID uuid);

	ItemClipGetRespDTO getItemClip(Long itemId, UUID uuid);
}
