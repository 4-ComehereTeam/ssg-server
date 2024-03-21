package com.comehere.ssgserver.image.application;

import org.springframework.stereotype.Service;

import com.comehere.ssgserver.image.dto.ItemWithImageRespDTO;
import com.comehere.ssgserver.image.infrastructure.ItemWithImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemWithImageServiceImpl implements ItemWithImageService{
	private final ItemWithImageRepository itemWithImageRepository;

	@Override
	public ItemWithImageRespDTO itemImages(Long itemId) {
		return ItemWithImageRespDTO.builder()
				.itemId(itemId)
				.images(itemWithImageRepository.findByItemId(itemId)
						.stream()
						.map(i -> i.getImage())
						.toList())
				.build();
	}
}
