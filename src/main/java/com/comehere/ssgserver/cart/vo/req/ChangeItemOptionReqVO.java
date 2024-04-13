package com.comehere.ssgserver.cart.vo.req;

import lombok.Getter;

@Getter
public class ChangeItemOptionReqVO {

	private Long itemId;
	
	private Long itemOptionId;

	private Long newItemOptionId;

	private Integer itemCount;
}
