package com.comehere.ssgserver.item.application;

import java.util.List;

import com.comehere.ssgserver.item.dto.ItemDetailRespDTO;
import com.comehere.ssgserver.item.vo.ItemListReqVO;

public interface ItemService {
	ItemDetailRespDTO getItemDetail(Long id);

	List<Long> getItemList(ItemListReqVO vo);
}
