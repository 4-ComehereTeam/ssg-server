package com.comehere.ssgserver.bundle.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.bundle.application.BundleService;
import com.comehere.ssgserver.bundle.domain.Bundle;
import com.comehere.ssgserver.bundle.dto.req.CreateBundleReqDTO;
import com.comehere.ssgserver.bundle.dto.resp.BundleItemRespDTO;
import com.comehere.ssgserver.bundle.dto.resp.BundleRespDTO;
import com.comehere.ssgserver.bundle.vo.req.CreateBundleReqVO;
import com.comehere.ssgserver.bundle.vo.resp.BundleItemRespVO;
import com.comehere.ssgserver.bundle.vo.resp.BundleListRespVO;
import com.comehere.ssgserver.common.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bundle")
@Slf4j
public class BundleController {
	private final BundleService bundleService;
	private final ModelMapper modelMapper;

	@PostMapping
	@Operation(summary = "묶음(특가) 상품 등록 API", description = "묶음(특가) 상품 등록")
	public BaseResponse<Void> createBundle(@RequestBody CreateBundleReqVO vo) {
		bundleService.createBundle(modelMapper.map(vo, CreateBundleReqDTO.class));
		return new BaseResponse<>();
	}

	@GetMapping
	@Operation(summary = "묶음(특가) 상품 ID 조회 API", description = "묶음(특가) 상품 목록 조회")
	public BaseResponse<BundleListRespVO> getBundleList(
			@RequestParam(required = false) Integer categoryId,
			@PageableDefault(size = 20) Pageable page) {
		log.info("묶음 상품 목록 조회 : categoryId={}", categoryId);
		return null;
	}

	@PutMapping("/{bundleId}")
	@Operation(summary = "묶음(특가) 상품 상태 변경 API", description = "묶음(특가) 상품 목록 조회")
	public BaseResponse<Bundle> updateBundleStatus(@PathVariable("bundleId") Long id) {
		return new BaseResponse<>(bundleService.updateBundleStatus(id));
	}

	@GetMapping("/{bundleId}")
	@Operation(summary = "특정 묶음 기본 정보 조회 API", description = "묶음(특가) 정보 조회")
	public BaseResponse<BundleRespDTO> getBundleDetail(@PathVariable("bundleId") Long id) {
		log.info("묶음 상품 기본 정보 조회 : bundleId={}", id);
		return new BaseResponse<>(bundleService.getBundleDetail(id));
	}

	@GetMapping("/item/{bundleId}")
	@Operation(summary = "특정 묶음 내 상품 정보 조회 API", description = "묶음 내의 상품 리스트 조회")
	public BaseResponse<BundleItemRespVO> getBundleItemList(@PathVariable("bundleId") Long bundleId) {
		log.info("묶음 내 상품 목록 조회 : bundleId={}", bundleId);
		return new BaseResponse<>(modelMapper.map(
				bundleService.getBundleItemList(bundleId), BundleItemRespVO.class));
	}
}
