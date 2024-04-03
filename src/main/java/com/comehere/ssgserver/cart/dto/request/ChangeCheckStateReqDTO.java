package com.comehere.ssgserver.cart.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeCheckStateReqDTO {

	private Long cartId;
	private Long itemOptionId;
}
