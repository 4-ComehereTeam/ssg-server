package com.comehere.ssgserver.cart.infrastructure;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.comehere.ssgserver.cart.dto.ItemCountDTO;
import com.comehere.ssgserver.cart.dto.req.ChangeStateReqDTO;
import com.comehere.ssgserver.cart.dto.req.DeleteItemReqDTO;
import com.comehere.ssgserver.cart.dto.req.ItemQuantityModifyReqDTO;

public interface CustomCartRepository {

	//장바구니에 담긴 상품 리스트 조회
	Page<ItemCountDTO> getCartId(UUID uuid, Pageable pageable);

	//상품 수량 변경
	Long updateItemQuantity(UUID uuid, Long ItemOptionId, int count);

	//상품 수량 감소
	Long minusItemQuantity(UUID uuid, ItemQuantityModifyReqDTO itemQuantityModifyReqDTO);

	//상품 체크 상태 변경
	Long updateCheckState(UUID uuid, ChangeStateReqDTO changeStateReqDTO);

	//상품 핀 상태 변경
	Long updatePinState(UUID uuid, ChangeStateReqDTO changeStateReqDTO);

	// 장바구니에서 상품 삭제
	Long deleteItem(UUID uuid, DeleteItemReqDTO deleteItemReqDTO);
}
