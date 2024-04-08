package com.comehere.ssgserver.cart.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemReqDTO {

	private Long itemOptionId;
	private Integer itemCount;
}
