package com.comehere.ssgserver.cart.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeStateReqDTO {

	private Long cartId;
	private Long itemOptionId;
}
