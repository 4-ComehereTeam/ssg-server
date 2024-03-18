package com.comehere.ssgserver.image.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.image.application.ItemWithImageService;
import com.comehere.ssgserver.image.dto.ItemImageRespDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ImageController {
	private final ItemWithImageService itemWithImageService;

	@GetMapping("/items/images/{id}")
	public ItemImageRespDTO itemWithImages(@PathVariable("id") Long id) {
		return itemWithImageService.itemImages(id);
	}
}
