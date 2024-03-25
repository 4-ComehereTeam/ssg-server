package com.comehere.ssgserver.purchase.presentation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.purchase.application.NonMemberPurchaseService;
import com.comehere.ssgserver.purchase.dto.NonMemberPurchaseGetDTO;
import com.comehere.ssgserver.purchase.vo.NonMemberPurchaseCreateVO;
import com.comehere.ssgserver.purchase.vo.NonMemberPurchaseDeleteVO;
import com.comehere.ssgserver.purchase.vo.NonMemberPurchaseGetVO;
import com.comehere.ssgserver.purchase.vo.NonMemberPurchaseOptionItemUpdateVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/purchase/non")
@RequiredArgsConstructor
@Tag(name = "non Member Purchase", description = "비회원 주문 컨트롤러")
public class NonMemberPurchaseController {

	private final NonMemberPurchaseService nonMemberPurchaseService;

	@PostMapping
	@Operation(summary = "비회원 주문 생성")
	public void createNonMemberPurchase(@RequestBody NonMemberPurchaseCreateVO nonMemberPurchaseCreateVO) {
		nonMemberPurchaseService.createNonMemberPurchase(nonMemberPurchaseCreateVO);
	}

	@GetMapping("/item")
	@Operation(summary = "비회원 주문 조회")
	public NonMemberPurchaseGetDTO getNonMemberPurchase(@ModelAttribute NonMemberPurchaseGetVO nonMemberPurchaseGetVO) {
		return nonMemberPurchaseService.getPurchase(nonMemberPurchaseGetVO);
	}

	@DeleteMapping
	@Operation(summary = "비회원 주문 삭제")
	public void deleteNonMemberPurchase(@RequestBody NonMemberPurchaseDeleteVO nonMemberPurchaseDeleteVO) {
		nonMemberPurchaseService.deleteNonMemberPurchase(nonMemberPurchaseDeleteVO);
	}

	@PutMapping("/option")
	@Operation(summary = "비회원 주문 수정(옵션 변경)")
	public void updateNonMemberPurchaseOptionItem(
			@RequestBody NonMemberPurchaseOptionItemUpdateVO nonMemberPurchaseOptionItemUpdateVO) {
		nonMemberPurchaseService.updateNonMemberPurchaseOptionItem(nonMemberPurchaseOptionItemUpdateVO);
	}
}
