package com.comehere.ssgserver.item.dto.resp;

import com.comehere.ssgserver.item.domain.ItemCalc;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemCalcRespDTO {
	private Long reviewCount;

	private Double averageStar;

	public static ItemCalcRespDTO toBuild(ItemCalc itemCalc) {
		return ItemCalcRespDTO.builder()
				.reviewCount(itemCalc.getReviewCount())
				.averageStar(itemCalc.getAverageStar())
				.build();
	}
}
