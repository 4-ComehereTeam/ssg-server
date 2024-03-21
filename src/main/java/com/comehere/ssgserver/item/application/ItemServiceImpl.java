package com.comehere.ssgserver.item.application;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.comehere.ssgserver.clip.domain.ItemClip;
import com.comehere.ssgserver.item.domain.Item;
import com.comehere.ssgserver.item.domain.ItemCalc;
import com.comehere.ssgserver.item.dto.ItemDetailRespDTO;
import com.comehere.ssgserver.item.infrastructual.ItemCalcRepository;
import com.comehere.ssgserver.item.infrastructual.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
	private final ItemRepository itemRepository;
	private final ItemCalcRepository itemCalcRepository;

	@Override
	public ItemDetailRespDTO getItemDetail(Long id) {
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 상품입니다."));

		ItemCalc itemCalc = itemCalcRepository.findById(item.getId())
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 상품입니다."));

		return ItemDetailRespDTO.builder()
				.itemName(item.getName())
				.itemCode(item.getCode())
				.price(item.getPrice())
				.discountRate(item.getDiscountRate())
				.description(item.getDescription())
				.status(item.getStatus())
				.averageStar(itemCalc.getAverageStar())
				.reviewCount(itemCalc.getReviewCount())
				.build();
	}
}
