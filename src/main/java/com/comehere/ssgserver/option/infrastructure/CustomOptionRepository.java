package com.comehere.ssgserver.option.infrastructure;

import java.util.List;

import com.comehere.ssgserver.option.domain.ItemOption;
import com.comehere.ssgserver.option.dto.resp.ColorDTO;
import com.comehere.ssgserver.option.dto.resp.ItemOptionInfoRespDTO;
import com.comehere.ssgserver.option.dto.resp.SizeDTO;

public interface CustomOptionRepository {
	List<ColorDTO> findColor(Long itemId);

	List<SizeDTO> findSize(Long itemId, Long colorId);

	List<ItemOption> findEtc(Long itemId, Long colorId, Long sizeId);

	Long findOptionId(Long reviewId);

	ItemOptionInfoRespDTO getOptionInfo(Long optionId);
}
