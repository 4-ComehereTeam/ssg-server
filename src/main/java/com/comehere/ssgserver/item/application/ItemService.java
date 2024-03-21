package com.comehere.ssgserver.item.application;

import com.comehere.ssgserver.item.dto.ItemDetailRespDTO;

public interface ItemService {
	ItemDetailRespDTO getItemDetail(Long id);
}
