package com.comehere.ssgserver.item.vo.resp;

import com.comehere.ssgserver.item.dto.resp.ItemCalcRespDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
// @Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
// @AllArgsConstructor
public class ItemCalcRespVO {
	private Long reviewCount;

	private Double averageStar;

	// public static ItemCalcRespVO toBuild(ItemCalcRespDTO dto) {
	// 	return ItemCalcRespVO.builder()
	// 			.reviewCount(dto.getReviewCount())
	// 			.averageStar(dto.getAverageStar())
	// 			.build();
	// }
}
