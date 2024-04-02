package com.comehere.ssgserver.cart.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cart")
@Tag(name = "Cart", description = "장바구니 컨트롤러")
public class CartController {

}
