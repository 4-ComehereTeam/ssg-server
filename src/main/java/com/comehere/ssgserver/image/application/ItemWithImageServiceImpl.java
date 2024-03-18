package com.comehere.ssgserver.image.application;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.stereotype.Service;

import com.comehere.ssgserver.image.domain.ItemWithImage;
import com.comehere.ssgserver.image.dto.ItemImageRespDTO;
import com.comehere.ssgserver.image.infrastructure.ItemWithImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemWithImageServiceImpl implements ItemWithImageService{
	private final ItemWithImageRepository itemWithImageRepository;

	@Override
	public ItemImageRespDTO itemImages(Long itemId) {
		return ItemImageRespDTO.builder()
				.itemId(itemId)
				.images(itemWithImageRepository.findByItemId(itemId)
						.stream()
						.map(i -> i.getImage())
						.toList())
				.build();
	}
}
