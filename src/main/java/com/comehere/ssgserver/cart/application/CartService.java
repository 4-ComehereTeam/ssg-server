package com.comehere.ssgserver.cart.application;

import java.util.UUID;

import com.comehere.ssgserver.cart.dto.request.AddProductReqDTO;
import com.comehere.ssgserver.cart.dto.request.ChangeCheckStateReqDTO;
import com.comehere.ssgserver.cart.dto.response.GetCartListRespDTO;

public interface CartService {

	Boolean addProductToCart(UUID uuid, AddProductReqDTO addProductReqDTO);

	Boolean changeProductChangeState(UUID uuid, ChangeCheckStateReqDTO changeCheckStateReqDTO);

	GetCartListRespDTO getCartList(UUID uuid);

}
