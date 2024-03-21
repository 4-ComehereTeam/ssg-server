package com.comehere.ssgserver.image.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.image.application.ImageService;
import com.comehere.ssgserver.image.dto.ItemImageRespDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "images", description = "이미지 관리 컨트롤러")
public class ImageController {
	private final ImageService imageService;

	@GetMapping("/items/images/{id}")
	@Operation(summary = "상품 이미지 조회 API", description = "상품 이미지 조회")
	public ItemImageRespDTO itemWithImages(@PathVariable("id") Long id) {
		return imageService.getItemImages(id);
	}
}
