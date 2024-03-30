package com.comehere.ssgserver.item.dto.resp;

import com.comehere.ssgserver.item.domain.Item;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemDetailRespDTO {
	private String itemName;

	private String itemCode;

	private Long price;

	private Integer discountRate;

	public static ItemDetailRespDTO toBuild(Item item) {
		return ItemDetailRespDTO.builder()
				.itemName(item.getName())
				.itemCode(item.getCode())
				.price(item.getPrice())
				.discountRate(item.getDiscountRate())
				.build();
	}
}
