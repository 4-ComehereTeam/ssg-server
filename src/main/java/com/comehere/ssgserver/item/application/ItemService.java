package com.comehere.ssgserver.item.application;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.comehere.ssgserver.item.dto.ItemCalcRespDTO;
import com.comehere.ssgserver.item.dto.ItemDetailRespDTO;
import com.comehere.ssgserver.item.vo.ItemListReqVO;
import com.comehere.ssgserver.item.dto.ItemListRespDTO;
import com.comehere.ssgserver.item.vo.ItemReqVO;

public interface ItemService {
	ItemDetailRespDTO getItemDetail(Long id);

	String getDescription(Long id);

	ItemCalcRespDTO getItemCalc(Long id);

	ItemListRespDTO getItemList(ItemListReqVO vo, Pageable page);

	void createItem(ItemReqVO vo);
}
