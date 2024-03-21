package com.comehere.ssgserver.item.vo;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemListRespVO {
	List<Long> itemIds;
}
