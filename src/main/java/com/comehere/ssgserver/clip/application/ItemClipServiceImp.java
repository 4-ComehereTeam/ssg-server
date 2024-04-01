package com.comehere.ssgserver.clip.application;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.clip.domain.ItemClip;
import com.comehere.ssgserver.clip.dto.req.ItemsClipDeleteReqDTO;
import com.comehere.ssgserver.clip.dto.req.ItemsClipGetRespDTO;
import com.comehere.ssgserver.clip.infrastructure.ItemClipRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemClipServiceImp implements ItemClipService {
	private final ItemClipRepository itemClipRepository;

	@Override
	@Transactional
	public void createItemClip(UUID uuid, Long itemId) {
		if (itemClipRepository.existsByUuidAndItemId(uuid, itemId)) {
			throw new IllegalArgumentException("이미 좋아요한 상품입니다.");
		}

		itemClipRepository.save(ItemClip.builder()
				.uuid(uuid)
				.itemId(itemId)
				.build());
	}

	@Override
	@Transactional
	public void deleteItemClip(UUID uuid, Long itemId) {
		ItemClip itemClip = itemClipRepository.findByUuidAndItemId(uuid, itemId)
				.orElseThrow(() -> new IllegalArgumentException("좋아요한 상품이 아닙니다."));

		itemClipRepository.delete(itemClip);
	}

	@Override
	@Transactional
	public void deleteItemsClip(UUID uuid, ItemsClipDeleteReqDTO vo) {
		itemClipRepository.deleteByUuidAndByIds(uuid, vo.getItemIds());
	}

	@Override
	@Transactional
	public ItemsClipGetRespDTO getItemsClip(UUID uuid) {
		List<ItemClip> itemclips = itemClipRepository.findByUuid(uuid);

		return ItemsClipGetRespDTO.builder()
				.itemIds(itemclips.stream()
						.map(ItemClip::getItemId)
						.collect(Collectors.toList()))
				.build();
	}
}
