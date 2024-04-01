package com.comehere.ssgserver.purchase.presentation;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.common.security.jwt.JWTUtil;
import com.comehere.ssgserver.purchase.application.PurchaseService;
import com.comehere.ssgserver.purchase.dto.req.PurchaseCreateReqDTO;
import com.comehere.ssgserver.purchase.dto.req.PurchaseListDeleteReqDTO;
import com.comehere.ssgserver.purchase.vo.req.PurchaseCreateReqVO;
import com.comehere.ssgserver.purchase.vo.req.PurchaseListDeleteReqVO;
import com.comehere.ssgserver.purchase.vo.resp.PurchasesGetRespVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/purchase")
@RequiredArgsConstructor
@Tag(name = "purchase", description = "주문 컨트롤러")
public class PurchaseController {
	private final PurchaseService purchaseService;

	private final JWTUtil jwtUtil;

	private final ModelMapper modelMapper;

	@PostMapping
	@Operation(summary = "주문 등록")
	public void createPurchase(
			@RequestBody PurchaseCreateReqVO vo,
			@RequestHeader("Authorization") String authorization) {

		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);
		purchaseService.createPurchase(modelMapper.map(vo, PurchaseCreateReqDTO.class), uuid);
	}

	// 주문 삭제
	@DeleteMapping
	@Operation(summary = "주문 삭제")
	public void deletePurchaseList(
			@RequestBody PurchaseListDeleteReqVO vo,
			@RequestHeader("Authorization") String authorization) {

		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);
		purchaseService.deletePurchaseList(modelMapper.map(vo, PurchaseListDeleteReqDTO.class), uuid);
	}

	// 주문 조회
	@GetMapping("/list")
	@Operation(summary = "주문 전체 조회")
	public List<PurchasesGetRespVO> getPurchases(
			@RequestHeader("Authorization") String authorization) {

		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);

		return purchaseService.getPurchases(uuid).stream()
				.map(dto -> modelMapper.map(dto, PurchasesGetRespVO.class))
				.collect(Collectors.toList());
	}

}
