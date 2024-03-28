package com.comehere.ssgserver.option.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.common.entity.BaseEntity;
import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.option.application.ItemOptionService;
import com.comehere.ssgserver.option.domain.Color;
import com.comehere.ssgserver.option.domain.ItemOption;
import com.comehere.ssgserver.option.dto.ColorRespDTO;
import com.comehere.ssgserver.option.dto.EtcRespDTO;
import com.comehere.ssgserver.option.dto.ItemOptionRespDTO;
import com.comehere.ssgserver.option.dto.OptionRespDTO;
import com.comehere.ssgserver.option.dto.SizeRespDTO;

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

	@GetMapping("/option/color/{id}")
	public BaseResponse<ColorRespDTO> getColors(@PathVariable("id") Long id) {
		return new BaseResponse<>(itemOptionService.getColors(id));
	}

	@GetMapping("/option/size/{id}")
	public BaseResponse<SizeRespDTO> getSizes(
			@PathVariable("id") Long itemId,
			@RequestParam(required = false) Long colorId) {
		return new BaseResponse<>(itemOptionService.getSizes(itemId, colorId));
	}

	@GetMapping("/option/etc/{id}")
	public BaseResponse<EtcRespDTO> getEtcs(
			@PathVariable("id") Long itemId,
			@RequestParam(required = false) Long colorId,
			@RequestParam(required = false) Long sizeId) {
		return new BaseResponse<>(itemOptionService.getEtcs(itemId, colorId, sizeId));
	}
}
