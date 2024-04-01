package com.comehere.ssgserver.clip.dto.req;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemsClipGetRespDTO {
	List<Long> itemIds = new ArrayList<>();

	@Builder
	public ItemsClipGetRespDTO(List<Long> itemIds) {
		this.itemIds = itemIds;
	}
}
