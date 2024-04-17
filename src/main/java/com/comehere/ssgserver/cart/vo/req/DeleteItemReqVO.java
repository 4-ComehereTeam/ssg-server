package com.comehere.ssgserver.cart.vo.req;

import java.util.List;

import lombok.Getter;

@Getter
public class DeleteItemReqVO {

	private List<Long> itemOptionIds;
}
