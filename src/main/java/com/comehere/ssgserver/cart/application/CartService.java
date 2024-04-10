package com.comehere.ssgserver.cart.application;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.comehere.ssgserver.cart.dto.req.AddItemReqDTO;
import com.comehere.ssgserver.cart.dto.req.ChangeItemOptionReqDTO;
import com.comehere.ssgserver.cart.dto.req.ChangeStateReqDTO;
import com.comehere.ssgserver.cart.dto.req.DeleteItemReqDTO;
import com.comehere.ssgserver.cart.dto.req.ItemQuantityModifyReqDTO;
import com.comehere.ssgserver.cart.dto.resp.GetCartListRespDTO;
import com.comehere.ssgserver.cart.dto.resp.ItemQuantityModifyRespDTO;

public interface CartService {

	//장바구니 상품 추가
	Boolean addItemToCart(UUID uuid, AddItemReqDTO addItemReqDTO);

	//상품 체크 상태 변경
	Boolean changeItemChangeState(UUID uuid, ChangeStateReqDTO changeStateReqDTO);

	// 상품 핀 상태 변경
	Boolean changeItemPinState(UUID uuid, ChangeStateReqDTO changeStateReqDTO);

	// 상품 옵션 변경
	Boolean changeItemOption(UUID uuid, ChangeItemOptionReqDTO changeItemOptionReqDTO);

	// 장바구니에 담긴 상품 리스트 조회
	GetCartListRespDTO getCartList(UUID uuid, Pageable pageable);

	// 장바구니에 담긴 상품 수량 감소
	ItemQuantityModifyRespDTO minusItemQuantity(UUID uuid, ItemQuantityModifyReqDTO itemQuantityModifyReqDTO);

	// 장바구니에 담긴 상품 수량 증가
	ItemQuantityModifyRespDTO plusItemQuantity(UUID uuid, ItemQuantityModifyReqDTO itemQuantityModifyReqDTO);

	// 장바구니에서 상품 삭제
	Boolean deleteItemFromCart(UUID uuid, DeleteItemReqDTO deleteItemReqDTO);
}
