package com.comehere.ssgserver.item.application;

import java.util.List;

import com.comehere.ssgserver.item.dto.ItemDetailRespDTO;
import com.comehere.ssgserver.item.vo.ItemListReqVO;
import com.comehere.ssgserver.item.vo.ItemListRespVO;

public interface ItemService {
	ItemDetailRespDTO getItemDetail(Long id);

	ItemListRespVO getItemList(ItemListReqVO vo);
}
