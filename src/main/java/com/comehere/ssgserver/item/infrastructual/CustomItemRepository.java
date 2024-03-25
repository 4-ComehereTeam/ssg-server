package com.comehere.ssgserver.item.infrastructual;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.comehere.ssgserver.item.vo.ItemListReqVO;

public interface CustomItemRepository {
	Slice<Long> getItemList(ItemListReqVO vo, Pageable page);
}
