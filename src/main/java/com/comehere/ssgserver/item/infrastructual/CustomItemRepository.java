package com.comehere.ssgserver.item.infrastructual;

import java.util.List;

import com.comehere.ssgserver.item.vo.ItemListReqVO;

public interface CustomItemRepository {
	List<Long> getItemList(ItemListReqVO vo);
}
