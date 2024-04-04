package com.comehere.ssgserver.option.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.option.application.ItemOptionService;
import com.comehere.ssgserver.option.dto.resp.ColorRespDTO;
import com.comehere.ssgserver.option.dto.resp.EtcRespDTO;
import com.comehere.ssgserver.option.dto.resp.ItemOptionRespDTO;
import com.comehere.ssgserver.option.dto.resp.OptionRespDTO;
import com.comehere.ssgserver.option.dto.resp.SizeRespDTO;
import com.comehere.ssgserver.option.vo.resp.ItemOptionIdRespVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class ItemOptionController {
	private final ItemOptionService itemOptionService;
	private final ModelMapper modelMapper;

	// @GetMapping("/items/options/{id}")
	public ItemOptionRespDTO itemOption(@PathVariable("id") Long itemId) {
		return itemOptionService.findByItemId(itemId);
	}

	@GetMapping("/items/options/{itemId}")
	public BaseResponse<OptionRespDTO> hasOptions(@PathVariable("itemId") Long itemId) {
		log.info("옵션 여부 조회 : itemId={}", itemId);
		return new BaseResponse<>(itemOptionService.hasOptions(itemId));
	}

	@GetMapping("/option/color/{itemId}")
	public BaseResponse<ColorRespDTO> getColors(@PathVariable("itemId") Long id) {
		log.info("색상 옵션 목록 조회 : itemId={}", id);
		return new BaseResponse<>(itemOptionService.getColors(id));
	}

	@GetMapping("/option/size/{itemId}")
	public BaseResponse<SizeRespDTO> getSizes(
			@PathVariable("itemId") Long itemId,
			@RequestParam(required = false) Long colorId) {
		log.info("사이즈 옵션 목록 조회 : itemId={}, colorId={}", itemId, colorId);
		return new BaseResponse<>(itemOptionService.getSizes(itemId, colorId));
	}

	@GetMapping("/option/etc/{itemId}")
	public BaseResponse<EtcRespDTO> getEtcs(
			@PathVariable("itemId") Long itemId,
			@RequestParam(required = false) Long colorId,
			@RequestParam(required = false) Long sizeId) {
		log.info("기타 옵션 목록 조회 : itemId={}, colorId={}, sizeId={}", itemId, colorId, sizeId);
		return new BaseResponse<>(itemOptionService.getEtcs(itemId, colorId, sizeId));
	}

	@GetMapping("/option/review/{reviewId}")
	public BaseResponse<ItemOptionIdRespVO> getItemOptions(@PathVariable("reviewId") Long reviewId) {
		log.info("리뷰 옵션 조회 : reviewId={}", reviewId);
		return new BaseResponse<>(modelMapper.map(
				itemOptionService.getOptionId(reviewId), ItemOptionIdRespVO.class));
	}

	@GetMapping("/option/{optionId}")
	public BaseResponse<?> getOptionInfo(@PathVariable("optionId") Long optionId) {
		log.info("옵션 값 조회 : optionId={}", optionId);
		return new BaseResponse<>();
	}
}
