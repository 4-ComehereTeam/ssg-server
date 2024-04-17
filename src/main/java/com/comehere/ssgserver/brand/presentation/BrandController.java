package com.comehere.ssgserver.brand.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.comehere.ssgserver.brand.vo.resp.BrandItemListRespVO;
import com.comehere.ssgserver.common.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "brand", description = "브랜드 관리 컨트롤러")
@Slf4j
public class BrandController {
	private final BrandService brandService;
	private final ModelMapper modelMapper;

	@GetMapping("/items/brand/{itemId}")
	@Operation(summary = "상품 별 브랜드 정보 조회 API", description = "특정 상품의 브랜드 정보")
	public BaseResponse<BrandInfoRespVO> getBrand(@PathVariable("itemId") Long itemId) {
		log.info("상품 브랜드 정보 조회 : itemId={}", itemId);
		return new BaseResponse<>(
				modelMapper.map(brandService.getBrand(itemId), BrandInfoRespVO.class));
	}

	@PostMapping("/brand")
	@Operation(summary = "브랜드 등록 API", description = "브랜드 등록")
	public BaseResponse<?> createBrand(@RequestBody BrandReqVO vo) {
		brandService.createBrand(modelMapper.map(vo, BrandReqDTO.class));
		return new BaseResponse<>();
	}

	@GetMapping("/brand/{brandId}")
	@Operation(summary = "브랜드 별 상품 목록 조회 API", description = "특정 브랜드의 상품 목록(ID) 조회")
	public BaseResponse<BrandItemListRespVO> brandItemList(
			@PathVariable("brandId") Long brandId,
			@PageableDefault(size = 5) Pageable page) {
		log.info("브랜드 별 상품 목록 조회 : brandId={}", brandId);
		return new BaseResponse<>(modelMapper.map(
				brandService.brandItemList(brandId, page), BrandItemListRespVO.class));
	}
}
