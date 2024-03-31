package com.comehere.ssgserver.brand.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.brand.application.BrandService;
import com.comehere.ssgserver.brand.dto.req.BrandReqDTO;
import com.comehere.ssgserver.brand.vo.req.BrandReqVO;
import com.comehere.ssgserver.brand.vo.resp.BrandInfoRespVO;
import com.comehere.ssgserver.common.response.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BrandController {
	private final BrandService brandService;
	private final ModelMapper modelMapper;

	@GetMapping("/items/brand/{itemId}")
	public BaseResponse<BrandInfoRespVO> getBrand(@PathVariable("itemId") Long itemId) {
		return new BaseResponse<>(
				modelMapper.map(brandService.getBrand(itemId), BrandInfoRespVO.class));
	}

	@PostMapping("/brand")
	public BaseResponse<?> createBrand(@RequestBody BrandReqVO vo) {
		brandService.createBrand(modelMapper.map(vo, BrandReqDTO.class));
		return new BaseResponse<>();
	}
}
