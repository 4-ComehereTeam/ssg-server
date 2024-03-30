package com.comehere.ssgserver.image.application;

import org.springframework.stereotype.Service;

import com.comehere.ssgserver.image.domain.ItemImage;
import com.comehere.ssgserver.image.dto.ImageDTO;
import com.comehere.ssgserver.image.dto.ItemImageRespDTO;
import com.comehere.ssgserver.image.dto.ItemThumbnailRespDTO;
import com.comehere.ssgserver.image.infrastructure.ItemImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
	private final ItemImageRepository itemImageRepository;

	@Override
	public ItemImageRespDTO getItemImages(Long itemId) {
		return ItemImageRespDTO.builder()
				.itemId(itemId)
				.itemImages(itemImageRepository.findByItemId(itemId).stream()
						.map(ImageDTO::new)
						.toList())
				.build();
	}

	@Override
	public ItemThumbnailRespDTO getItemThumbnail(Long itemId) {
		ItemImage thumbnail = itemImageRepository.findThumbnail(itemId);

		return ItemThumbnailRespDTO.builder()
				.itemId(thumbnail.getItemId())
				.imageId(thumbnail.getId())
				.url(thumbnail.getImageUrl())
				.alt(thumbnail.getAlt())
				.build();
	}
}
