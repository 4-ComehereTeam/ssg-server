package com.comehere.ssgserver.image.application;

import java.util.List;

import com.comehere.ssgserver.image.dto.ItemImageRespDTO;

public interface ItemWithImageService {
	ItemImageRespDTO itemImages(Long itemId);
}
