package com.comehere.ssgserver.purchase.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.purchase.application.NonPurchaseService;
import com.comehere.ssgserver.purchase.application.PurchaseService;
import com.comehere.ssgserver.purchase.dto.req.NonPurchaseGetReqDTO;
import com.comehere.ssgserver.purchase.dto.req.PurchaseCreateReqDTO;
import com.comehere.ssgserver.purchase.dto.resp.NonPurchaseGetRespDTO;
import com.comehere.ssgserver.purchase.vo.req.NonPurchaseGetReqVO;
import com.comehere.ssgserver.purchase.vo.req.PurchaseCreateReqVO;
import com.comehere.ssgserver.purchase.vo.req.PurchaseListDeleteReqVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/non/purchase")
@RequiredArgsConstructor
@Tag(name = "non purchase", description = "비회원 주문 컨트롤러")
public class NonPurchaseController {
	private final NonPurchaseService nonPurchaseService;

	private final PurchaseService purchaseService;

	private final ModelMapper modelMapper;

	@PostMapping
	@Operation(summary = "비회원 주문 등록")
	public BaseResponse<?> createNonPurchase(@RequestBody PurchaseCreateReqVO vo) {
		purchaseService.createPurchase(modelMapper.map(vo, PurchaseCreateReqDTO.class), null);

		return new BaseResponse<>();
	}

	/*@DeleteMapping
	@Operation(summary = "비회원 주문 리스트 (상품) 취소")
	public void deleteNonPurchaseList() {
	}

	@DeleteMapping
	@Operation(summary = "비회원 주문 삭제")
	public void deleteNonPurchase() {
	}*/
	@GetMapping
	@Operation(summary = "비회원 주문 조회")
	public BaseResponse<NonPurchaseGetRespDTO> getNonPurchase(@RequestBody NonPurchaseGetReqVO vo) {
		return new BaseResponse<>(nonPurchaseService.getNonPurchase(modelMapper.map(vo, NonPurchaseGetReqDTO.class)));
	}
}
