package com.comehere.ssgserver.cart.application;

import java.util.UUID;

import com.comehere.ssgserver.cart.dto.request.AddProductReqDTO;
import com.comehere.ssgserver.cart.dto.request.ChangeStateReqDTO;
import com.comehere.ssgserver.cart.dto.response.GetCartListRespDTO;

public interface CartService {

	Boolean addProductToCart(UUID uuid, AddProductReqDTO addProductReqDTO);

	Boolean changeProductChangeState(UUID uuid, ChangeStateReqDTO changeStateReqDTO);

	Boolean changeProductPinState(UUID uuid, ChangeStateReqDTO changeStateReqDTO);

	GetCartListRespDTO getCartList(UUID uuid);

}
