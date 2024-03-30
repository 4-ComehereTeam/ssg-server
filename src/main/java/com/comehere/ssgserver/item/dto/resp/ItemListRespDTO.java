package com.comehere.ssgserver.item.dto.resp;

import java.util.List;

import org.springframework.data.domain.Slice;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ItemListRespDTO {
	List<Long> itemIds;

	Boolean hasNext;

	public static ItemListRespDTO toBuild(Slice<Long> slice) {
		return ItemListRespDTO.builder()
				.itemIds(slice.getContent())
				.hasNext(slice.hasNext())
				.build();
	}
}
