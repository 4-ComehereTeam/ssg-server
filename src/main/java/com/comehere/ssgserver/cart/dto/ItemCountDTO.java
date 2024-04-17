package com.comehere.ssgserver.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemCountDTO {

	private Long itemId;

	private Long itemOptionId;

	private Integer itemCount;

	private Boolean pinStatus;

	private Boolean itemCheck;
}
