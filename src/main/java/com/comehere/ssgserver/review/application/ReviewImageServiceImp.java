package com.comehere.ssgserver.review.application;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.common.exception.BaseException;
import com.comehere.ssgserver.common.response.BaseResponseStatus;
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
				.orElseThrow(() -> new BaseException(BaseResponseStatus.REVIEW_NOT_FOUND));

		if (!review.getUuid().equals(uuid)) {
			throw new BaseException(BaseResponseStatus.REVIEW_IMAGE_AUTHORITY);
		}

		if (reviewImageRepository.findByReview(review).size() >= 3) {
			throw new BaseException(BaseResponseStatus.REVIEW_IMAGE_LIMIT);
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
				.orElseThrow(() -> new BaseException(BaseResponseStatus.REVIEW_IMAGE_NOT_FOUND));

		if (!reviewImage.getReview().getUuid().equals(uuid)) {
			throw new BaseException(BaseResponseStatus.REVIEW_IMAGE_AUTHORITY);
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
				.orElseThrow(() -> new BaseException(BaseResponseStatus.REVIEW_IMAGE_NOT_FOUND));

		if (!reviewImage.getReview().getUuid().equals(uuid)) {
			throw new BaseException(BaseResponseStatus.REVIEW_IMAGE_AUTHORITY);
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
		Slice<ReviewImageListDTO> reviews = reviewImageRepository.getReviewImageList(itemCode, page);

		return ReviewImageListRespDTO.builder()
				.itemCode(itemCode)
				.images(reviews.getContent())
				.currentPage(reviews.getNumber())
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
