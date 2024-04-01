package com.comehere.ssgserver.item.application;

import org.springframework.data.domain.Pageable;

import com.comehere.ssgserver.item.dto.req.ItemListReqDTO;
import com.comehere.ssgserver.item.dto.req.ItemReqDTO;
import com.comehere.ssgserver.item.dto.resp.ItemCalcRespDTO;
import com.comehere.ssgserver.item.dto.resp.ItemDetailRespDTO;
import com.comehere.ssgserver.item.dto.resp.ItemImageRespDTO;
import com.comehere.ssgserver.item.dto.resp.ItemListRespDTO;
import com.comehere.ssgserver.item.dto.resp.ItemThumbnailRespDTO;

public interface ItemService {
	ItemDetailRespDTO getItemDetail(Long id);

	String getDescription(Long id);

	ItemCalcRespDTO getItemCalc(Long id);

	ItemListRespDTO getItemList(ItemListReqDTO dto, Pageable page);

	void createItem(ItemReqDTO dto);

	ItemImageRespDTO getItemImages(Long itemId);

	ItemThumbnailRespDTO getItemThumbnail(Long itemId);
}
