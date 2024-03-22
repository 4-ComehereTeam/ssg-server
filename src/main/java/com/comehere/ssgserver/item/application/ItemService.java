package com.comehere.ssgserver.item.application;

import com.comehere.ssgserver.item.dto.ItemCalcRespDTO;
import com.comehere.ssgserver.item.dto.ItemDetailRespDTO;
import com.comehere.ssgserver.item.vo.ItemListReqVO;
import com.comehere.ssgserver.item.vo.ItemListRespVO;

public interface ItemService {
	ItemDetailRespDTO getItemDetail(Long id);

	String getDescription(Long id);

	ItemCalcRespDTO getItemCalc(Long id);

	ItemListRespVO getItemList(ItemListReqVO vo);
}
