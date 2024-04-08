package com.comehere.ssgserver.item.application;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.common.exception.BaseException;
import com.comehere.ssgserver.common.response.BaseResponseStatus;
import com.comehere.ssgserver.item.domain.Item;
import com.comehere.ssgserver.item.domain.ItemCalc;
import com.comehere.ssgserver.item.domain.RecentViewItem;
import com.comehere.ssgserver.item.dto.req.DeleteRecentViewReqDTO;
import com.comehere.ssgserver.item.dto.req.ItemListReqDTO;
import com.comehere.ssgserver.item.dto.req.ItemReqDTO;
import com.comehere.ssgserver.item.dto.resp.ImageDTO;
import com.comehere.ssgserver.item.dto.resp.ItemCalcRespDTO;
import com.comehere.ssgserver.item.dto.resp.ItemDetailRespDTO;
import com.comehere.ssgserver.item.dto.resp.ItemImageListRespDTO;
import com.comehere.ssgserver.item.dto.resp.ItemListRespDTO;
import com.comehere.ssgserver.item.dto.resp.ItemThumbnailRespDTO;
import com.comehere.ssgserver.item.dto.resp.RecentViewDTO;
import com.comehere.ssgserver.item.dto.resp.RecentViewListRespDTO;
import com.comehere.ssgserver.item.infrastructual.ItemCalcRepository;
import com.comehere.ssgserver.item.infrastructual.ItemImageRepository;
import com.comehere.ssgserver.item.infrastructual.ItemRepository;
import com.comehere.ssgserver.item.infrastructual.RecentViewItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {
	private final ItemRepository itemRepository;
	private final ItemCalcRepository itemCalcRepository;
	private final ItemImageRepository itemImageRepository;
	private final RecentViewItemRepository recentViewItemRepository;

	@Override
	public ItemDetailRespDTO getItemDetail(Long id) {
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_NOT_FOUND));

		return ItemDetailRespDTO.toBuild(item);
	}

	@Override
	public String getDescription(Long id) {
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_NOT_FOUND));

		return item.getDescription();
	}

	@Override
	public ItemCalcRespDTO getItemCalc(Long id) {
		return ItemCalcRespDTO.toBuild(itemCalcRepository.findByItemId(id)
				.orElseGet(() -> ItemCalc.builder()
						.averageStar(0.0)
						.reviewCount(0L)
						.build()));
	}

	@Override
	public ItemListRespDTO getItemList(ItemListReqDTO dto, Pageable page) {
		return ItemListRespDTO.toBuild(itemRepository.getItemList(dto, page));
	}

	@Override
	@Transactional
	public void createItem(ItemReqDTO dto) {
		itemRepository.save(Item.builder()
				.name(dto.getName())
				.code(UUID.randomUUID().toString())
				.price(dto.getPrice())
				.discountRate(0)
				.description(dto.getDescription())
				.status((short)1)
				.build());
	}

	@Override
	public ItemImageListRespDTO getItemImages(Long itemId) {
		return ItemImageListRespDTO.builder()
				.itemId(itemId)
				.itemImages(itemImageRepository.findByItemId(itemId).stream()
						.map(ImageDTO::new)
						.toList())
				.build();
	}

	@Override
	public ItemThumbnailRespDTO getItemThumbnail(Long itemId) {
		return ItemThumbnailRespDTO.toBuild(itemImageRepository.findThumbnail(itemId));
	}

	@Override
	@Transactional
	public String createRecentViewItem(Long itemId, UUID uuid) {
		Optional<RecentViewItem> optional = recentViewItemRepository.findByItemIdAndUuid(itemId, uuid);

		if(optional.isEmpty()) {
			recentViewItemRepository.save(RecentViewItem.builder()
					.itemId(itemId)
					.uuid(uuid)
					.viewDate(LocalDateTime.now())
					.build());

			return "INSERT RECENT VIEW ITEM";
		} else {
			recentViewItemRepository.save(RecentViewItem.builder()
					.id(optional.get().getId())
					.viewDate(LocalDateTime.now())
					.build());

			return "UPDATE RECENT VIEW ITEM";
		}
	}

	@Override
	public RecentViewListRespDTO getRecentViewItems(UUID uuid, Pageable page) {
		Slice<RecentViewItem> items = recentViewItemRepository.findByUuid(uuid, page);

		return RecentViewListRespDTO.builder()
				.recentItems(items.stream()
						.map(RecentViewDTO::new)
						.toList())
				.currentPage(items.getNumber())
				.hasNext(items.hasNext())
				.build();
	}

	@Override
	@Transactional
	public void deleteRecentViewItems(UUID uuid, DeleteRecentViewReqDTO dto) {
		recentViewItemRepository.deleteByUuidAndIds(uuid, dto.getRecentIds());
	}
}
