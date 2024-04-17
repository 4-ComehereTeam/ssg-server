package com.comehere.ssgserver.cart.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddItemReqDTO {

	private Long itemId;

	private Long itemOptionId;

	private Integer itemCount;
}
