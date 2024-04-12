package com.comehere.ssgserver.clip.dto.resp;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemsClipGetRespDTO {
	private List<Long> itemIds = new ArrayList<>();

	@Builder
	public ItemsClipGetRespDTO(List<Long> itemIds) {
		this.itemIds = itemIds;
	}
}
