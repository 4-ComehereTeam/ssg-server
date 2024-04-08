package com.comehere.ssgserver.purchase.presentation;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.common.security.jwt.JWTUtil;
import com.comehere.ssgserver.purchase.application.PurchaseService;
import com.comehere.ssgserver.purchase.dto.req.PurchaseCreateReqDTO;
import com.comehere.ssgserver.purchase.dto.req.PurchaseListDeleteReqDTO;
import com.comehere.ssgserver.purchase.dto.resp.PurchaseCreateRespDTO;
import com.comehere.ssgserver.purchase.vo.req.PurchaseCreateReqVO;
import com.comehere.ssgserver.purchase.vo.req.PurchaseListDeleteReqVO;
import com.comehere.ssgserver.purchase.vo.resp.PurchaseCreateRespVO;
import com.comehere.ssgserver.purchase.vo.resp.PurchaseListGetRespVO;
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
	public BaseResponse<PurchaseCreateRespVO> createPurchase(
			@RequestBody PurchaseCreateReqVO vo,
			@RequestHeader(value = "Authorization", required = false) String authorization) {

		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);
		PurchaseCreateRespDTO dto = purchaseService.createPurchase(modelMapper.map(vo, PurchaseCreateReqDTO.class),
				uuid);

		return new BaseResponse<>(modelMapper.map(dto, PurchaseCreateRespVO.class));
	}

	@DeleteMapping("/list")
	@Operation(summary = "주문 리스트(상품) 취소")
	public BaseResponse<Void> deletePurchaseList(
			@RequestBody PurchaseListDeleteReqVO vo,
			@RequestHeader("Authorization") String authorization) {

		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);
		purchaseService.deletePurchaseList(modelMapper.map(vo, PurchaseListDeleteReqDTO.class), uuid);

		return new BaseResponse<>();
	}

	@DeleteMapping("/{purchaseCode}")
	@Operation(summary = "주문 삭제", description = "주문 삭제시 연관 주문 리스트(상품) 같이 삭제")
	public BaseResponse<Void> deletePurchase(
			@PathVariable("purchaseCode") String purchaseCode,
			@RequestHeader("Authorization") String authorization) {
		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);
		purchaseService.deletePurchase(purchaseCode, uuid);

		return new BaseResponse<>();
	}

	@GetMapping
	@Operation(summary = "주문 전체 조회")
	public BaseResponse<List<PurchasesGetRespVO>> getPurchases(@RequestHeader("Authorization") String authorization) {
		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);

		return new BaseResponse<>(purchaseService.getPurchases(uuid).stream()
				.map(dto -> modelMapper.map(dto, PurchasesGetRespVO.class))
				.collect(Collectors.toList()));
	}

	@GetMapping("/{purchaseListId}")
	@Operation(summary = "주문 조회 (item_id, item_name, created_date, status)")
	public BaseResponse<PurchaseListGetRespVO> getPurchaseList(
			@PathVariable("purchaseListId") Long purchaseListId,
			@RequestHeader("Authorization") String authorization) {

		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);

		return new BaseResponse<>(modelMapper.map(purchaseService.getPurchaseList(purchaseListId, uuid),
				PurchaseListGetRespVO.class));
	}

}
