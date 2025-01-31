package com.comehere.ssgserver.clip.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.clip.domain.ItemClip;
import com.comehere.ssgserver.clip.dto.req.ItemsClipDeleteReqDTO;
import com.comehere.ssgserver.clip.dto.resp.ItemClipGetRespDTO;
import com.comehere.ssgserver.clip.dto.resp.ItemsClipGetRespDTO;
import com.comehere.ssgserver.clip.infrastructure.ItemClipRepository;
import com.comehere.ssgserver.common.exception.BaseException;
import com.comehere.ssgserver.common.response.BaseResponseStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemClipServiceImp implements ItemClipService {
	private final ItemClipRepository itemClipRepository;

	@Override
	@Transactional
	public void createItemClip(UUID uuid, Long itemId) {
		if (itemClipRepository.existsByUuidAndItemId(uuid, itemId)) {
			throw new BaseException(BaseResponseStatus.CLIP_ITEM_ALREADY_ADDED);
		}

		itemClipRepository.save(ItemClip.builder()
				.uuid(uuid)
				.itemId(itemId)
				.build());
	}

	@Override
	@Transactional
	public void deleteItemClip(UUID uuid, Long itemId) {
		itemClipRepository.deleteItemClip(uuid, itemId);
	}

	@Override
	@Transactional
	public void deleteItemsClip(UUID uuid, ItemsClipDeleteReqDTO dto) {
		itemClipRepository.deleteByUuidAndByIds(uuid, dto.getItemIds());
	}

	@Override
	@Transactional
	public ItemsClipGetRespDTO getItemsClip(UUID uuid) {
		return ItemsClipGetRespDTO.builder()
				.itemIds(itemClipRepository.findItemIdsByUuid(uuid))
				.build();
	}

	@Override
	public ItemClipGetRespDTO getItemClip(Long itemId, UUID uuid) {
		return ItemClipGetRespDTO.builder()
				.isCliped(itemClipRepository.existsByUuidAndItemId(uuid, itemId))
				.build();
	}
}
