package com.comehere.ssgserver.option.application;

import com.comehere.ssgserver.option.dto.ItemOptionRespDTO;
import com.comehere.ssgserver.option.dto.OptionRespDTO;

public interface ItemOptionService {
	ItemOptionRespDTO findByItemId(Long itemId);

	OptionRespDTO hasOptions(Long itemId);
}
