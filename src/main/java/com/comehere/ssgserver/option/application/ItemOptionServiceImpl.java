package com.comehere.ssgserver.option.application;

import org.springframework.stereotype.Service;

import com.comehere.ssgserver.option.domain.ItemOption;
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
}
