package com.comehere.ssgserver.cart.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductReqDTO {

	private Long itemOptionId;
	private Integer itemCount;
}
