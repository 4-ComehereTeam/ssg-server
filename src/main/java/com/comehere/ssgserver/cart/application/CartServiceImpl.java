package com.comehere.ssgserver.cart.application;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.cart.domain.Cart;
import com.comehere.ssgserver.cart.dto.req.AddItemReqDTO;
import com.comehere.ssgserver.cart.dto.req.ChangeItemOptionReqDTO;
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
	@Transactional
	public void addItemToCart(UUID uuid, AddItemReqDTO addItemReqDTO) {

		if (cartRepository.existsByUuidAndItemOptionId(uuid, addItemReqDTO.getItemOptionId())) {
			// 이미 장바구니에 담긴 상품이라면 수량만 증가
			cartRepository.updateItemQuantity(uuid, addItemReqDTO.getItemOptionId(), addItemReqDTO.getItemCount());
		} else {
			// 장바구니에 담긴 상품이 아니라면 새로 추가
			cartRepository.save(addItem(uuid, addItemReqDTO.getItemId(), addItemReqDTO.getItemOptionId(),
					addItemReqDTO.getItemCount()));
		}
	}

	// 장바구니에 담긴 상품 리스트 조회
	@Override
	public GetCartListRespDTO getCartList(UUID uuid, Pageable pageable) {

		return GetCartListRespDTO.builder()
				.itemOptions(cartRepository.getCartId(uuid, pageable))
				.build();
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

	// 상품 옵션 변경
	@Override
	public Boolean changeItemOption(UUID uuid, ChangeItemOptionReqDTO changeItemOptionReqDTO) {
		cartRepository.deleteByItemOptionIdAndUuid(changeItemOptionReqDTO.getItemOptionId(), uuid);
		cartRepository.save(
				addItem(uuid, changeItemOptionReqDTO.getItemId(), changeItemOptionReqDTO.getNewItemOptionId(),
						changeItemOptionReqDTO.getItemCount()));
		return true;
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
		cartRepository.updateItemQuantity(uuid, itemQuantityModifyReqDTO.getItemOptionId(), 1);
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
	private Cart addItem(UUID uuid, Long itemId, Long itemOptionId, Integer itemCount) {

		return Cart.builder()
				.uuid(uuid)
				.itemId(itemId)
				.itemOptionId(itemOptionId)
				.itemCount(itemCount)
				.itemCheck(false)
				.memberAddressId(addressRepository.getDefaultAddress(uuid))
				.pinStatus(false)
				.build();
	}
}
