package com.comehere.ssgserver.cart.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeStateReqDTO {

	private Long cartId;
	private Long itemOptionId;
}
