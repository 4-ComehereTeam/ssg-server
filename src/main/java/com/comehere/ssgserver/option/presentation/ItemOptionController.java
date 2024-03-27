package com.comehere.ssgserver.option.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.option.application.ItemOptionService;
import com.comehere.ssgserver.option.dto.ItemOptionRespDTO;
import com.comehere.ssgserver.option.dto.OptionRespDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ItemOptionController {
	private final ItemOptionService itemOptionService;

	// @GetMapping("/items/options/{id}")
	public ItemOptionRespDTO itemOption(@PathVariable("id") Long itemId) {
		return itemOptionService.findByItemId(itemId);
	}

	@GetMapping("/items/options/{id}")
	public BaseResponse<OptionRespDTO> hasOptions(@PathVariable("id") Long itemId) {
		return new BaseResponse<>(itemOptionService.hasOptions(itemId));
	}
}
