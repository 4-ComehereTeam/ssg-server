package com.comehere.ssgserver.clip.application;

import com.comehere.ssgserver.clip.dto.ItemClipDto;
import com.comehere.ssgserver.clip.dto.ItemIdsDTO;

public interface ItemClipService {
	void createItemClip(ItemClipDto itemClipDto);

	void deleteItemClip(ItemClipDto itemClipDto);

	ItemIdsDTO getClipList(Long memberId);
}
