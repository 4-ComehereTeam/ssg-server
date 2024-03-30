package com.comehere.ssgserver.item.vo.resp;

import java.util.List;

import com.comehere.ssgserver.item.dto.resp.ItemListRespDTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemListRespVO {
	List<Long> itemIds;

	Boolean hasNext;
}
