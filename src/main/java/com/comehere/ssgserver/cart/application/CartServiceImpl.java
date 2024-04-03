package com.comehere.ssgserver.cart.application;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.comehere.ssgserver.cart.domain.Cart;
import com.comehere.ssgserver.cart.dto.request.AddProductReqDTO;
import com.comehere.ssgserver.cart.dto.response.GetCartListRespDTO;
import com.comehere.ssgserver.cart.infrastructure.CartRepository;
import com.comehere.ssgserver.purchase.infrastructure.AddressRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;
	private final AddressRepository addressRepository;

	@Override
	public Boolean addProductToCart(UUID uuid, AddProductReqDTO addProductReqDTO) {

		cartRepository.save(addProduct(uuid, addProductReqDTO));
		return true;
	}

	@Override
	public GetCartListRespDTO getCartList(UUID uuid) {

		GetCartListRespDTO getCartListRespDTO = new GetCartListRespDTO();
		getCartListRespDTO.setItemOptions(cartRepository.getCartId(uuid));

		return getCartListRespDTO;
	}

	private Cart addProduct(UUID uuid, AddProductReqDTO addProductReqDTO) {

		return Cart.builder()
				.uuid(uuid)
				.itemOptionId(addProductReqDTO.getItemOptionId())
				.itemCount(addProductReqDTO.getItemCount())
				.itemCheck(false)
				.memberAddressId(addressRepository.getDefaultAddress(uuid))
				.pinStatus(false)
				.build();
	}
}
