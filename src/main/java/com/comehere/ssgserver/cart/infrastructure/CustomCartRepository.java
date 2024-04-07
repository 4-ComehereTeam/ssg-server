package com.comehere.ssgserver.cart.infrastructure;

import java.util.List;
import java.util.UUID;

import com.comehere.ssgserver.cart.dto.ItemCountDTO;
import com.comehere.ssgserver.cart.dto.req.AddItemReqDTO;
import com.comehere.ssgserver.cart.dto.req.ChangeStateReqDTO;
import com.comehere.ssgserver.cart.dto.req.DeleteItemReqDTO;
import com.comehere.ssgserver.cart.dto.req.ItemQuantityModifyReqDTO;

public interface CustomCartRepository {

	//장바구니에 담긴 상품 리스트 조회
	List<ItemCountDTO> getCartId(UUID uuid);

	Long updateItemQuantity(UUID uuid, AddItemReqDTO addItemReqDTO);

	//상품 수량 감소
	Long minusItemQuantity(UUID uuid, ItemQuantityModifyReqDTO itemQuantityModifyReqDTO);

	//상품 수량 증가
	Long plusItemQuantity(UUID uuid, ItemQuantityModifyReqDTO itemQuantityModifyReqDTO);

	//상품 체크 상태 변경
	Long updateCheckState(UUID uuid, ChangeStateReqDTO changeStateReqDTO);

	//상품 핀 상태 변경
	Long updatePinState(UUID uuid, ChangeStateReqDTO changeStateReqDTO);

	Long deleteItem(UUID uuid, DeleteItemReqDTO deleteItemReqDTO);
}
