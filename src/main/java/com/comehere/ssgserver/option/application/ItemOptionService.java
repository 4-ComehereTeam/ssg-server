package com.comehere.ssgserver.option.application;

import com.comehere.ssgserver.option.dto.ItemOptionRespDTO;

public interface ItemOptionService {
	ItemOptionRespDTO findByItemId(Long itemId);
}
