package com.comehere.ssgserver.item.dto.resp;

import java.util.List;

import org.springframework.data.domain.Slice;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ItemListRespDTO {
	private List<Long> itemIds;

	private Integer currentPage;

	private  Boolean hasNext;

	public static ItemListRespDTO toBuild(Slice<Long> slice) {
		return ItemListRespDTO.builder()
				.itemIds(slice.getContent())
				.currentPage(slice.getNumber())
				.hasNext(slice.hasNext())
				.build();
	}
}
