package com.comehere.ssgserver.cart.application;

import java.util.UUID;

import com.comehere.ssgserver.cart.dto.req.AddItemReqDTO;
import com.comehere.ssgserver.cart.dto.req.ChangeStateReqDTO;
import com.comehere.ssgserver.cart.dto.req.DeleteItemReqDTO;
import com.comehere.ssgserver.cart.dto.resp.GetCartListRespDTO;

public interface CartService {

	//장바구니 상품 추가
	Boolean addItemToCart(UUID uuid, AddItemReqDTO addItemReqDTO);

	//상품 체크 상태 변경
	Boolean changeItemChangeState(UUID uuid, ChangeStateReqDTO changeStateReqDTO);

	// 상품 핀 상태 변경
	Boolean changeItemPinState(UUID uuid, ChangeStateReqDTO changeStateReqDTO);

	// 장바구니에 담긴 상품 리스트 조회
	GetCartListRespDTO getCartList(UUID uuid);

	// 장바구니에서 상품 삭제
	Boolean deleteItemFromCart(UUID uuid, DeleteItemReqDTO deleteItemReqDTO);
}
