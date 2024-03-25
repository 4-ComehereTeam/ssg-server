package com.comehere.ssgserver.image.application;

import com.comehere.ssgserver.image.dto.ItemImageRespDTO;
import com.comehere.ssgserver.image.dto.ItemThumbnailRespDTO;
import com.comehere.ssgserver.image.vo.ReviewImageVO;

public interface ImageService {
	ItemImageRespDTO getItemImages(Long itemId);

	void updateReviewImage(ReviewImageVO reviewImageVO);

	void deleteReviewImage(Long reviewImageId);

	void deleteReviewImages(Long reviewId);

	ItemThumbnailRespDTO getItemThumbnail(Long itemId);
}
