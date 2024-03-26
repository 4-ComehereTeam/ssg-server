package com.comehere.ssgserver.purchase.presentation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.purchase.application.MemberPurchaseService;
import com.comehere.ssgserver.purchase.dto.PurchaseIdDTO;
import com.comehere.ssgserver.purchase.vo.MemberPurchaseDeliveriesItemsCreateVO;
import com.comehere.ssgserver.purchase.vo.MemberPurchaseIdVO;
import com.comehere.ssgserver.purchase.vo.MemberPurchaseItemsCreateVO;
import com.comehere.ssgserver.purchase.vo.MemberPurchaseQuickCreateVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/purchase")
@RequiredArgsConstructor
@Tag(name = "Member Purchase", description = "회원 주문 컨트롤러")
public class MemberPurchaseController {
	private final MemberPurchaseService memberPurchaseService;

	@PostMapping
	@Operation(summary = "회원 바로 주문 생성")
	public void createMemberQuickPurchase(@RequestBody MemberPurchaseQuickCreateVO vo) {
		memberPurchaseService.createMemberQuickPurchase(vo);
	}

	@PostMapping("/items")
	@Operation(summary = "회원 상품 2개 이상 주문")
	public void createMemberQuickPurchaseItems(@RequestBody MemberPurchaseItemsCreateVO vo) {
		memberPurchaseService.createMemberPurchaseItems(vo);
	}

	@PostMapping("/deliveries/items")
	@Operation(summary = "회원 상품 여러곳 한방에 주문 생성")
	public void createMemberPurchaseItemsWithDeliveries(@RequestBody MemberPurchaseDeliveriesItemsCreateVO vo) {
		memberPurchaseService.createMemberPurchaseItemsWithDeliveries(vo);
	}

	@GetMapping("/items")
	@Operation(summary = "회원 주문 조회")
	public PurchaseIdDTO getMemberPurchases(@RequestParam Long memberId) {
		System.out.println("memberId = " + memberId);
		return memberPurchaseService.getMemberPurchases(memberId);
	}

	@DeleteMapping
	@Operation(summary = "회원 주문 삭제")
	public void deleteMemberPurchase(@RequestBody MemberPurchaseIdVO memberPurchaseIdVO) {
		memberPurchaseService.deleteMemberPurchase(memberPurchaseIdVO);
	}

	@DeleteMapping("/item")
	@Operation(summary = "회원 주문의 단품 삭제")
	public void deleteMemberPurchase(@RequestParam Long purchaseListId) {
		memberPurchaseService.deletePurchaseList(purchaseListId);
	}
}
