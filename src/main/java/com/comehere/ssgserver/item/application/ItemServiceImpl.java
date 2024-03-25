package com.comehere.ssgserver.item.application;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.category.infrastructure.DetailCategoryRepository;
import com.comehere.ssgserver.item.domain.Item;
import com.comehere.ssgserver.item.domain.ItemCalc;
import com.comehere.ssgserver.item.dto.ItemCalcRespDTO;
import com.comehere.ssgserver.item.dto.ItemDetailRespDTO;
import com.comehere.ssgserver.item.infrastructual.ItemCalcRepository;
import com.comehere.ssgserver.item.infrastructual.ItemRepository;
import com.comehere.ssgserver.item.vo.ItemListReqVO;
import com.comehere.ssgserver.item.dto.ItemListRespDTO;
import com.comehere.ssgserver.item.vo.ItemReqVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService { // 기본 CRUD API 생성하기
	private final ItemRepository itemRepository;
	private final ItemCalcRepository itemCalcRepository;

	@Override
	public ItemDetailRespDTO getItemDetail(Long id) {
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 상품입니다."));

		return ItemDetailRespDTO.builder()
				.itemName(item.getName())
				.itemCode(item.getCode())
				.price(item.getPrice())
				.discountRate(item.getDiscountRate())
				.build();
	}

	@Override
	public String getDescription(Long id) {
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 상품입니다."));

		return item.getDescription();
	}

	@Override
	public ItemCalcRespDTO getItemCalc(Long id) {
		ItemCalc itemCalc = itemCalcRepository.findByItemId(id)
				.orElseGet(() -> ItemCalc.builder()
						.averageStar(0.0)
						.reviewCount(0L)
						.build());

		return ItemCalcRespDTO.builder()
				.reviewCount(itemCalc.getReviewCount())
				.averageStar(itemCalc.getAverageStar())
				.build();
	}

	@Override
	public ItemListRespDTO getItemList(ItemListReqVO vo, Pageable page) {
		Slice<Long> slice = itemRepository.getItemList(vo, page);

		return ItemListRespDTO.builder()
				.itemIds(slice.getContent())
				.hasNext(slice.hasNext())
				.build();
	}

	@Override
	@Transactional
	public void createItem(ItemReqVO vo) {
		Item item = Item.builder()
				.name(vo.getName())
				.code(UUID.randomUUID().toString())
				.price(vo.getPrice())
				.discountRate(0)
				.description(vo.getDescription())
				.status((short)1)
				.build();

		itemRepository.save(item);
	}
}
