package com.comehere.ssgserver.item.application;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.comehere.ssgserver.item.dto.req.DeleteRecentViewReqDTO;
import com.comehere.ssgserver.item.dto.req.ItemCountReqDTO;
import com.comehere.ssgserver.item.dto.req.ItemListReqDTO;
import com.comehere.ssgserver.item.dto.req.ItemReqDTO;
import com.comehere.ssgserver.item.dto.resp.BestItemRespDTO;
import com.comehere.ssgserver.item.dto.resp.ItemCalcRespDTO;
import com.comehere.ssgserver.item.dto.resp.ItemCountRespDTO;
import com.comehere.ssgserver.item.dto.resp.ItemDetailRespDTO;
import com.comehere.ssgserver.item.dto.resp.ItemImageListRespDTO;
import com.comehere.ssgserver.item.dto.resp.ItemListRespDTO;
import com.comehere.ssgserver.item.dto.resp.ItemThumbnailRespDTO;
import com.comehere.ssgserver.item.dto.resp.RecentViewListRespDTO;

public interface ItemService {
	ItemDetailRespDTO getItemDetail(Long id);

	String getDescription(Long id);

	ItemCalcRespDTO getItemCalc(Long id);

	ItemListRespDTO getItemList(ItemListReqDTO dto, Pageable page);

	void createItem(ItemReqDTO dto);

	ItemImageListRespDTO getItemImages(Long itemId);

	ItemThumbnailRespDTO getItemThumbnail(Long itemId);

	String createRecentViewItem(Long itemId, UUID uuid);

	RecentViewListRespDTO getRecentViewItems(UUID uuid, Pageable page);

	void deleteRecentViewItems(UUID uuid, DeleteRecentViewReqDTO dto);

	ItemCountRespDTO getCount(ItemCountReqDTO dto);

	BestItemRespDTO getBestItems(Integer bigCategoryId);
}
