package com.comehere.ssgserver.bundle.presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.bundle.application.BundleService;
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
}
