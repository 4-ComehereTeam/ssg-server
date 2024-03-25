package com.comehere.ssgserver.item.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemListRespDTO {
	List<Long> itemIds;
	Boolean hasNext;
}
