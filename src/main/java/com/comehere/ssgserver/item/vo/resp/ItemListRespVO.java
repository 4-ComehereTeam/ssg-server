package com.comehere.ssgserver.item.vo.resp;

import java.util.List;

import com.comehere.ssgserver.item.dto.resp.ItemListRespDTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemListRespVO {
	List<Long> itemIds;

	Boolean hasNext;

	public static ItemListRespVO toBuild(ItemListRespDTO dto) {
		return ItemListRespVO.builder()
				.itemIds(dto.getItemIds())
				.hasNext(dto.getHasNext())
				.build();
	}
}
