package com.comehere.ssgserver.option.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.comehere.ssgserver.option.domain.ItemOption;
import com.comehere.ssgserver.option.dto.resp.ColorDTO;
import com.comehere.ssgserver.option.dto.resp.ColorRespDTO;
import com.comehere.ssgserver.option.dto.resp.EtcDTO;
import com.comehere.ssgserver.option.dto.resp.EtcRespDTO;
import com.comehere.ssgserver.option.dto.resp.HasOptionRespDTO;
import com.comehere.ssgserver.option.dto.resp.ItemOptionIdRespDTO;
import com.comehere.ssgserver.option.dto.resp.ItemOptionInfoRespDTO;
import com.comehere.ssgserver.option.dto.resp.SizeDTO;
import com.comehere.ssgserver.option.dto.resp.SizeRespDTO;
import com.comehere.ssgserver.option.infrastructure.ItemOptionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemOptionServiceImpl implements ItemOptionService {
	private final ItemOptionRepository itemOptionRepository;

	@Override
	public HasOptionRespDTO hasOptions(Long itemId) {
		ItemOption io = itemOptionRepository.findFirstByItemId(itemId);

		return HasOptionRespDTO.builder()
				.itemId(itemId)
				.hasColor(io.getColor() != null)
				.hasSize(io.getSize() != null)
				.hasEtc(io.getEtc() != null)
				.build();
	}

	@Override
	public ColorRespDTO getColors(Long itemId) {
		return ColorRespDTO.builder()
				.itemId(itemId)
				.colors(itemOptionRepository.findColor(itemId))
				.build();
	}

	@Override
	public SizeRespDTO getSizes(Long itemId, Long colorId) {
		return SizeRespDTO.builder()
				.itemId(itemId)
				.colorId(colorId)
				.sizes(itemOptionRepository.findSize(itemId, colorId))
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

	@Override
	public ItemOptionIdRespDTO getOptionId(Long reviewId) {
		return ItemOptionIdRespDTO.builder()
				.itemOptionId(itemOptionRepository.findOptionId(reviewId))
				.build();
	}

	@Override
	public ItemOptionInfoRespDTO getOptionInfo(Long itemId) {
		return itemOptionRepository.getOptionInfo(itemId);
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
