package com.comehere.ssgserver.cart.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeItemOptionReqDTO {

	private Long itemId;

	private Long itemOptionId;

	private Long newItemOptionId;

	private Integer itemCount;
}
