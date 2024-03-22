package com.comehere.ssgserver.clip.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.clip.application.ItemClipService;
import com.comehere.ssgserver.clip.dto.ItemClipDto;
import com.comehere.ssgserver.clip.dto.ItemIdRespDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/clip")
@RequiredArgsConstructor
@Tag(name = "item clip", description = "상품 좋아요 컨트롤러")
public class ItemClipController {
	private final ItemClipService itemClipService;

	@Operation(summary = "상품 좋아요")
	@PostMapping("/item")
	public void createItemClip(@RequestBody ItemClipDto itemClipDto) {
		itemClipService.createItemClip(itemClipDto);
	}

	@Operation(summary = "상품 좋아요 취소")
	@DeleteMapping("/item")
	public void deleteItemClip(@RequestBody ItemClipDto itemClipDto) {
		itemClipService.deleteItemClip(itemClipDto);
	}

	@Operation(summary = "상품 좋아요 목록 조회")
	@GetMapping("/items")
	public List<ItemIdRespDTO> getClipList(Long memberId) {
		return itemClipService.getClipList(memberId);
	}
}

