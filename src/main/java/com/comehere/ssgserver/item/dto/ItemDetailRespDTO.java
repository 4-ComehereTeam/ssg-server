package com.comehere.ssgserver.item.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemDetailRespDTO {
	private String itemName;

	private String itemCode;

	private Long price;

	private Integer discountRate;
}
