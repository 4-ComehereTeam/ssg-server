package com.comehere.ssgserver.cart.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeItemOptionReqDTO {

	private Long itemOptionId;

	private Long newItemOptionId;

	private Integer itemCount;
}
