package com.comehere.ssgserver.cart.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.cart.domain.Cart;
import com.comehere.ssgserver.cart.dto.req.AddItemReqDTO;
import com.comehere.ssgserver.cart.dto.req.ChangeStateReqDTO;
import com.comehere.ssgserver.cart.dto.req.DeleteItemReqDTO;
import com.comehere.ssgserver.cart.dto.req.ItemQuantityModifyReqDTO;
import com.comehere.ssgserver.cart.dto.resp.GetCartListRespDTO;
import com.comehere.ssgserver.cart.dto.resp.ItemQuantityModifyRespDTO;
import com.comehere.ssgserver.cart.infrastructure.CartRepository;
import com.comehere.ssgserver.purchase.infrastructure.AddressRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;
	private final AddressRepository addressRepository;

	// 장바구니에 상품 추가
	@Override
	public Boolean addItemToCart(UUID uuid, AddItemReqDTO addItemReqDTO) {

		cartRepository.save(addItem(uuid, addItemReqDTO));
		return true;
	}

	// 장바구니에 담긴 상품 리스트 조회
	@Override
	public GetCartListRespDTO getCartList(UUID uuid) {

		// setter를 사용할 떄 메서드 명에 명식적으로 적어서 사용하는 것이 좋다.
		GetCartListRespDTO getCartListRespDTO = new GetCartListRespDTO();
		getCartListRespDTO.setItemOptions(cartRepository.getCartId(uuid));

		return getCartListRespDTO;
	}

	// 상품 체크 상태 변경
	@Override
	@Transactional
	public Boolean changeItemChangeState(UUID uuid, ChangeStateReqDTO changeStateReqDTO) {

		return cartRepository.updateCheckState(uuid, changeStateReqDTO) > 0;
	}

	// 상품 핀 상태 변경
	@Override
	@Transactional
	public Boolean changeItemPinState(UUID uuid, ChangeStateReqDTO changeStateReqDTO) {

		return cartRepository.updatePinState(uuid, changeStateReqDTO) > 0;
	}

	// 상품 수량 감소
	@Override
	@Transactional
	public ItemQuantityModifyRespDTO minusItemQuantity(UUID uuid, ItemQuantityModifyReqDTO itemQuantityModifyReqDTO) {
		cartRepository.minusItemQuantity(uuid, itemQuantityModifyReqDTO);
		return ItemQuantityModifyRespDTO.builder()
				.itemCount(cartRepository.findByItemOptionIdAndUuid(itemQuantityModifyReqDTO.getItemOptionId(), uuid))
				.build();
	}

	// 상품 수량 증가
	@Override
	@Transactional
	public ItemQuantityModifyRespDTO plusItemQuantity(UUID uuid, ItemQuantityModifyReqDTO itemQuantityModifyReqDTO) {
		cartRepository.plusItemQuantity(uuid, itemQuantityModifyReqDTO);
		return ItemQuantityModifyRespDTO.builder()
				.itemCount(cartRepository.findByItemOptionIdAndUuid(itemQuantityModifyReqDTO.getItemOptionId(), uuid))
				.build();
	}

	// 장바구니에서 상품 삭제
	@Override
	@Transactional
	public Boolean deleteItemFromCart(UUID uuid, DeleteItemReqDTO deleteItemReqDTO) {
		return cartRepository.deleteItem(uuid, deleteItemReqDTO) > 0;
	}

	// 상품 생성
	private Cart addItem(UUID uuid, AddItemReqDTO addItemReqDTO) {

		return Cart.builder()
				.uuid(uuid)
				.itemOptionId(addItemReqDTO.getItemOptionId())
				.itemCount(addItemReqDTO.getItemCount())
				.itemCheck(false)
				.memberAddressId(addressRepository.getDefaultAddress(uuid))
				.pinStatus(false)
				.build();
	}
}
