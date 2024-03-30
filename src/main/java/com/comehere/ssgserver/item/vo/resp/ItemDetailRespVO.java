package com.comehere.ssgserver.item.vo.resp;

import com.comehere.ssgserver.item.dto.resp.ItemDetailRespDTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemDetailRespVO {
	private String itemName;

	private String itemCode;

	private Long price;

	private Integer discountRate;

	public static ItemDetailRespVO toBuild(ItemDetailRespDTO dto) {
		return ItemDetailRespVO.builder()
				.discountRate(dto.getDiscountRate())
				.price(dto.getPrice())
				.itemCode(dto.getItemCode())
				.itemName(dto.getItemName())
				.build();
	}
}
