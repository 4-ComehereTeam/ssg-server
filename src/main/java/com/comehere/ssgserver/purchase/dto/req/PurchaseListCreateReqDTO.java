package com.comehere.ssgserver.purchase.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PurchaseListCreateReqDTO {
	private Long itemOptionId;

	private Integer count;

}
