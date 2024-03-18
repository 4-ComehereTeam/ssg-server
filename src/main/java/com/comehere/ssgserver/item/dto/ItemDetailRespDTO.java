package com.comehere.ssgserver.item.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemDetailRespDTO {
	private String itemName;

	private String itemCode;

	private String description;

	private Long price;

	private Integer discountRate;

	private Short status;

	private Long reviewCount;

	private Double averageStar;
}
