package com.comehere.ssgserver.option.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.comehere.ssgserver.option.domain.Color;
import com.comehere.ssgserver.option.domain.ItemOption;
import com.comehere.ssgserver.option.dto.ColorDTO;
import com.comehere.ssgserver.option.dto.ColorRespDTO;
import com.comehere.ssgserver.option.dto.ItemOptionRespDTO;
import com.comehere.ssgserver.option.dto.OptionDTO;
import com.comehere.ssgserver.option.dto.OptionRespDTO;
import com.comehere.ssgserver.option.infrastructure.ItemOptionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemOptionServiceImpl implements ItemOptionService {
	private final ItemOptionRepository itemOptionRepository;

	@Override
	public ItemOptionRespDTO findByItemId(Long itemId) {
		return ItemOptionRespDTO.builder()
				.itemId(itemId)
				.options(itemOptionRepository.findOptions(itemId).stream()
						.map(OptionDTO::new)
						.toList())
				.build();
	}

	@Override
	public OptionRespDTO hasOptions(Long itemId) {
		ItemOption io = itemOptionRepository.findFirstByItemId(itemId);

		return OptionRespDTO.builder()
				.itemId(itemId)
				.hasColor(io.getColor() != null)
				.hasSize(io.getSize() != null)
				.hasEtc(io.getEtc() != null)
				.build();
	}

	@Override
	public ColorRespDTO getColors(Long itemId) {
		List<ItemOption> itemOption = itemOptionRepository.findByColorItemId(itemId);

		return ColorRespDTO.builder()
				.itemId(itemId)
				.colors(itemOption.stream()
						.map(this::createColor)
						.toList())
				.build();
	}

	private ColorDTO createColor(ItemOption io) {
		return ColorDTO.builder()
				.optionId(io.getId())
				.colorId(io.getColor().getId())
				.value(io.getColor().getValue())
				.stock(io.getStock())
				.build();
	}

}
