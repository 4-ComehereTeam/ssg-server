package com.comehere.ssgserver.cart.application;

import java.util.UUID;

import com.comehere.ssgserver.cart.dto.request.AddProductReqDTO;

public interface CartService {

	Boolean addProductToCart(UUID uuid, AddProductReqDTO addProductReqDTO);
}
