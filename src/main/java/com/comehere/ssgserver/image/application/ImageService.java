package com.comehere.ssgserver.image.application;

import com.comehere.ssgserver.image.dto.ItemImageRespDTO;
import com.comehere.ssgserver.image.dto.ItemThumbnailRespDTO;

public interface ImageService {
	ItemImageRespDTO getItemImages(Long itemId);

	ItemThumbnailRespDTO getItemThumbnail(Long itemId);
}
