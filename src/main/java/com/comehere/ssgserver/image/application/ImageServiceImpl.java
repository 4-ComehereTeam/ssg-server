package com.comehere.ssgserver.image.application;

import org.springframework.stereotype.Service;

import com.comehere.ssgserver.image.domain.ReviewImage;
import com.comehere.ssgserver.image.dto.ImageDTO;
import com.comehere.ssgserver.image.dto.ItemImageRespDTO;
import com.comehere.ssgserver.image.infrastructure.ItemImageRepository;
import com.comehere.ssgserver.image.infrastructure.ReviewImageRepository;
import com.comehere.ssgserver.image.vo.ReviewImageVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
	private final ItemImageRepository itemImageRepository;

	private final ReviewImageRepository reviewImageRepository;

	@Override
	public ItemImageRespDTO getItemImages(Long itemId) {
		return ItemImageRespDTO.builder()
				.itemId(itemId)
				.itemImages(itemImageRepository.findByItemId(itemId).stream()
						.map(ImageDTO::new)
						.toList())
				.build();
	}

	@Override
	public void updateReviewImage(ReviewImageVO reviewImageVO) {
		ReviewImage reviewImage = getReviewImage(reviewImageVO.getReviewImageId());

		reviewImage.updateReviewImage(reviewImageVO);

		reviewImageRepository.save(reviewImage);
	}

	@Override
	public void deleteReviewImage(Long reviewImageId) {
		ReviewImage reviewImage = getReviewImage(reviewImageId);

		if (reviewImage.getThumbnail()) {
			throw new IllegalArgumentException("썸네일 이미지는 삭제할 수 없습니다.");
		}

		reviewImageRepository.delete(reviewImage);
	}

	@Override
	public void deleteReviewImages(Long reviewId) {
		reviewImageRepository.deleteAll(reviewImageRepository.findByReviewId(reviewId));
	}

	private ReviewImage getReviewImage(Long reviewImageId) {
		return reviewImageRepository.findById(reviewImageId)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이미지입니다."));
	}
}
