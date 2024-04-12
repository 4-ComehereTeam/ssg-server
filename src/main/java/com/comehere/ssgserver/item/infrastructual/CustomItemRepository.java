package com.comehere.ssgserver.item.infrastructual;

import java.util.List;
import java.util.function.LongFunction;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.comehere.ssgserver.item.dto.req.ItemCountReqDTO;
import com.comehere.ssgserver.item.dto.req.ItemListReqDTO;

public interface CustomItemRepository {
	Slice<Long> getItemList(ItemListReqDTO dto, Pageable page);

	Long getCount(ItemCountReqDTO dto);

	List<Long> getBestItems(Integer bigCategoryId);
}
