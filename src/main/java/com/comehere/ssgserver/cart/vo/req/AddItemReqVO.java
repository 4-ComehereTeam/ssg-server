package com.comehere.ssgserver.cart.vo.req;

import lombok.Getter;

@Getter
public class AddItemReqVO {

	private Long itemId;

	private Long itemOptionId;

	private Integer itemCount;
}
