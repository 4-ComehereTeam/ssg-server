package com.comehere.ssgserver.item.infrastructual;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.comehere.ssgserver.item.dto.req.ItemListReqDTO;

public interface CustomItemRepository {
	Slice<Long> getItemList(ItemListReqDTO dto, Pageable page);
}
