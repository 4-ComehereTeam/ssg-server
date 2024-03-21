package com.comehere.ssgserver.image.application;

import com.comehere.ssgserver.image.dto.ItemWithImageRespDTO;

public interface ItemWithImageService {
	ItemWithImageRespDTO itemImages(Long itemId);
}
