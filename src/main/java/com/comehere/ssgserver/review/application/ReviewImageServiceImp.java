package com.comehere.ssgserver.review.application;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.review.domain.Review;
import com.comehere.ssgserver.review.domain.ReviewImage;
import com.comehere.ssgserver.review.dto.req.ReviewImageCreateDTO;
import com.comehere.ssgserver.review.dto.req.ReviewImageReqDTO;
import com.comehere.ssgserver.review.dto.req.ReviewImageUpdateReqDTO;
import com.comehere.ssgserver.review.dto.resp.ReviewImageDTO;
import com.comehere.ssgserver.review.dto.resp.ReviewImageListDTO;
import com.comehere.ssgserver.review.dto.resp.ReviewImageListRespDTO;
import com.comehere.ssgserver.review.dto.resp.ReviewImageRespDTO;
import com.comehere.ssgserver.review.infrastructure.ReviewImageRepository;
import com.comehere.ssgserver.review.infrastructure.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewImageServiceImp implements ReviewImageService {
	private final ReviewImageRepository reviewImageRepository;

	private final ReviewRepository reviewRepository;

	@Override
	public void createReviewImage(ReviewImageCreateDTO dto, UUID uuid) {
		Review review = reviewRepository.findById(dto.getReviewId())
				.orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));

		if (!review.getUuid().equals(uuid)) {
			throw new IllegalArgumentException("리뷰 이미지를 등록할 권한이 없습니다.");
		}

		if (reviewImageRepository.findByReview(review).size() >= 3) {
			throw new IllegalArgumentException("리뷰 이미지는 3개까지 등록할 수 있습니다.");
		}

		builderReviewImage(review, dto.getImageUrl(), dto.getAlt());
	}

	@Override
	public void createReviewImage(ReviewImageReqDTO dto, Review review) {
		builderReviewImage(review, dto.getImageUrl(), dto.getAlt());
	}

	@Override
	@Transactional
	public void deleteReviewImages(Review review) {
		reviewImageRepository.deleteAll(reviewImageRepository.findByReview(review));
	}

	@Override
	public void deleteReviewImage(Long reviewImageId, UUID uuid) {
		ReviewImage reviewImage = reviewImageRepository.findById(reviewImageId)
				.orElseThrow(() -> new IllegalArgumentException("리뷰 이미지를 찾을 수 없습니다."));

		if (!reviewImage.getReview().getUuid().equals(uuid)) {
			throw new IllegalArgumentException("리뷰 이미지를 삭제할 권한이 없습니다.");
		}

		reviewImageRepository.delete(reviewImage);
	}

	@Override
	@Transactional
	public void updateReviewImages(List<ReviewImageUpdateReqDTO> dtos, UUID uuid) {
		dtos.forEach(dto -> {
			updateReviewImage(dto, uuid);
		});
	}

	@Override
	@Transactional
	public void updateReviewImage(ReviewImageUpdateReqDTO dto, UUID uuid) {
		ReviewImage reviewImage = reviewImageRepository.findById(dto.getReviewImageId())
				.orElseThrow(() -> new IllegalArgumentException("리뷰 이미지를 찾을 수 없습니다."));

		if (!reviewImage.getReview().getUuid().equals(uuid)) {
			throw new IllegalArgumentException("리뷰 이미지를 수정할 권한이 없습니다.");
		}

		reviewImageRepository.save(ReviewImage.builder()
				.id(reviewImage.getId())
				.imageUrl(dto.getImageUrl())
				.alt(dto.getAlt())
				.build());
	}

	@Override
	public ReviewImageRespDTO getReviewImages(Long reviewId) {
		return ReviewImageRespDTO.builder()
				.reviewId(reviewId)
				.images(reviewImageRepository.findByReviewId(reviewId).stream()
						.map(ReviewImageDTO::new)
						.toList())
				.build();
	}

	@Override
	public ReviewImageListRespDTO getReviewImageList(String itemCode, Pageable page) {
		// Slice<ReviewImage> reviewImages = reviewImageRepository.findReviewImages(itemCode, page);
		//
		// return ReviewImageListRespDTO.builder()
		// 		.itemCode(itemCode)
		// 		.images(reviewImages.stream()
		// 				.map(ReviewImageListDTO::new)
		// 				.toList())
		// 		.hasNext(reviewImages.hasNext())
		// 		.build();

		Slice<ReviewImageListDTO> reviews = reviewImageRepository.getReviewImageList(itemCode, page);

		return ReviewImageListRespDTO.builder()
				.itemCode(itemCode)
				.images(reviews.getContent())
				.hasNext(reviews.hasNext())
				.build();
	}

	private void builderReviewImage(Review review, String imageUrl, String alt) {
		reviewImageRepository.save(ReviewImage.builder()
				.review(review)
				.imageUrl(imageUrl)
				.alt(alt)
				.build());
	}
}
