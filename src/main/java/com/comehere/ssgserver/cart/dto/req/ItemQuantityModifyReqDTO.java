package com.comehere.ssgserver.cart.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemQuantityModifyReqDTO {
	private Long itemOptionId;
}
