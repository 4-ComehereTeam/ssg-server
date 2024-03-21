package com.comehere.ssgserver.clip.application;

import java.util.List;

import com.comehere.ssgserver.clip.dto.ItemClipDto;
import com.comehere.ssgserver.clip.dto.ItemIdRespDTO;

public interface ItemClipService {
	void createItemClip(ItemClipDto itemClipDto);

	void deleteItemClip(ItemClipDto itemClipDto);

	List<ItemIdRespDTO> getClipList(Long memberId);
}
