package com.comehere.ssgserver.bundle.presentation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.bundle.application.BundleService;
import com.comehere.ssgserver.bundle.domain.Bundle;
import com.comehere.ssgserver.bundle.dto.BundleListRespDTO;
import com.comehere.ssgserver.bundle.dto.BundleRespDTO;
import com.comehere.ssgserver.bundle.vo.BundleReqVO;
import com.comehere.ssgserver.common.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bundle")
public class BundleController {
	private final BundleService bundleService;

	@PostMapping
	@Operation(summary = "묶음(특가) 상품 등록 API", description = "묶음(특가) 상품 등록")
	public BaseResponse<?> createBundle(@RequestBody BundleReqVO vo) {
		bundleService.createBundle(vo);
		return new BaseResponse<>();
	}

	@GetMapping
	@Operation(summary = "묶음(특가) 상품 ID 조회 API", description = "묶음(특가) 상품 목록 조회")
	public Page<Bundle> getBundleList(@PageableDefault(size = 20) Pageable page) {
		return bundleService.getBundleList(page);
	}

	@PutMapping("/{id}")
	@Operation(summary = "묶음(특가) 상품 상태 변경 API", description = "묶음(특가) 상품 목록 조회")
	public BaseResponse<Bundle> updateBundleStatus(@PathVariable("id") Long id) {
		return new BaseResponse<>(bundleService.updateBundleStatus(id));
	}

	@GetMapping("/{id}")
	@Operation(summary = "특정 묶음 기본 정보 조회 API", description = "묶음(특가) 정보 조회")
	public BaseResponse<BundleRespDTO> getBundleDetail(@PathVariable("id") Long id) {
		return new BaseResponse<>(bundleService.getBundleDetail(id));
	}
}
