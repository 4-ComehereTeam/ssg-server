package com.comehere.ssgserver.option.dto.resp;

import com.comehere.ssgserver.option.domain.ItemOption;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemStockRespDTO {
	private Long itemOptionId;

	private Integer stock;

	public static ItemStockRespDTO toBuild(ItemOption io) {
		return ItemStockRespDTO.builder()
				.itemOptionId(io.getId())
				.stock(io.getStock())
				.build();
	}
}
