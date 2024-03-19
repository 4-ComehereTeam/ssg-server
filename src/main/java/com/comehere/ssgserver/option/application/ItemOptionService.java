package com.comehere.ssgserver.option.application;

import java.util.List;

import com.comehere.ssgserver.option.domain.ItemOption;
import com.comehere.ssgserver.option.dto.ItemOptionRespDTO;

public interface ItemOptionService {
	ItemOptionRespDTO findByItemId(Long itemId);
}
