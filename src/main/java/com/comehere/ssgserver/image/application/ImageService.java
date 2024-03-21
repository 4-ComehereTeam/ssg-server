package com.comehere.ssgserver.image.application;

import com.comehere.ssgserver.image.dto.ItemImageRespDTO;

public interface ImageService {
	ItemImageRespDTO getItemImages(Long itemId);
}
