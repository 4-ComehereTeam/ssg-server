package com.comehere.ssgserver.item.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemCalcRespDTO {
	private Long reviewCount;

	private Double averageStar;
}
