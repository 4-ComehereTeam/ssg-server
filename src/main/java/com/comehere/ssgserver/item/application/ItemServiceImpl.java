package com.comehere.ssgserver.item.application;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.item.domain.Item;
import com.comehere.ssgserver.item.domain.ItemCalc;
import com.comehere.ssgserver.item.dto.req.ItemListReqDTO;
import com.comehere.ssgserver.item.dto.req.ItemReqDTO;
import com.comehere.ssgserver.item.dto.resp.ItemCalcRespDTO;
import com.comehere.ssgserver.item.dto.resp.ItemDetailRespDTO;
import com.comehere.ssgserver.item.dto.resp.ItemListRespDTO;
import com.comehere.ssgserver.item.infrastructual.ItemCalcRepository;
import com.comehere.ssgserver.item.infrastructual.ItemRepository;
import com.comehere.ssgserver.item.vo.req.ItemReqVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {
	private final ItemRepository itemRepository;
	private final ItemCalcRepository itemCalcRepository;

	@Override
	public ItemDetailRespDTO getItemDetail(Long id) {
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 상품입니다."));

		return ItemDetailRespDTO.toBuild(item);
	}

	@Override
	public String getDescription(Long id) {
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 상품입니다."));

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
}
