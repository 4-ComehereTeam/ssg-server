package com.comehere.ssgserver.option.application;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.comehere.ssgserver.item.domain.Item;
import com.comehere.ssgserver.option.domain.Color;
import com.comehere.ssgserver.option.domain.ItemOption;
import com.comehere.ssgserver.option.dto.ColorDTO;
import com.comehere.ssgserver.option.dto.ColorRespDTO;
import com.comehere.ssgserver.option.dto.EtcDTO;
import com.comehere.ssgserver.option.dto.EtcRespDTO;
import com.comehere.ssgserver.option.dto.ItemOptionRespDTO;
import com.comehere.ssgserver.option.dto.OptionDTO;
import com.comehere.ssgserver.option.dto.OptionRespDTO;
import com.comehere.ssgserver.option.dto.SizeDTO;
import com.comehere.ssgserver.option.dto.SizeRespDTO;
import com.comehere.ssgserver.option.infrastructure.ItemOptionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemOptionServiceImpl implements ItemOptionService {
	private final ItemOptionRepository itemOptionRepository;
	private final ModelMapper modelMapper;

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

		// OptionRespDTO optionRespDTO = ;

		// return OptionRespDTO.builder()
		// 		.itemId(itemId)
		// 		.hasColor(io.getColor() != null)
		// 		.hasSize(io.getSize() != null)
		// 		.hasEtc(io.getEtc() != null)
		// 		.build();

		return modelMapper.map(io, OptionRespDTO.class);
	}

	@Override
	public ColorRespDTO getColors(Long itemId) {
		List<ItemOption> itemOption = itemOptionRepository.findColorByItemId(itemId);

		return ColorRespDTO.builder()
				.itemId(itemId)
				.colors(itemOption.stream()
						.map(this::createColor)
						.toList())
				.build();
	}

	@Override
	public SizeRespDTO getSizes(Long itemId, Long colorId) {
		List<ItemOption> itemOption = itemOptionRepository.findSize(itemId, colorId);

		return SizeRespDTO.builder()
				.itemId(itemId)
				.colorId(colorId)
				.sizes(itemOption.stream()
						.map(this::createSize)
						.toList())
				.build();
	}

	@Override
	public EtcRespDTO getEtcs(Long itemId, Long colorId, Long sizeId) {
		List<ItemOption> itemOption = itemOptionRepository.findEtc(itemId, colorId,sizeId);

		return EtcRespDTO.builder()
				.itemId(itemId)
				.colorId(colorId)
				.sizeId(sizeId)
				.etcs(itemOption.stream()
						.map(ItemOptionServiceImpl::createEtc)
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

	private SizeDTO createSize(ItemOption io) {
		return SizeDTO.builder()
				.optionId(io.getId())
				.sizeId(io.getSize().getId())
				.value(io.getSize().getValue())
				.stock(io.getStock())
				.build();
	}

	private static EtcDTO createEtc(ItemOption io) {
		return EtcDTO.builder()
				.optionId(io.getId())
				.etcId(io.getEtc().getId())
				.value(io.getEtc().getValue())
				.stock(io.getStock())
				.build();
	}
}
