package com.comehere.ssgserver.review.application;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.common.exception.BaseException;
import com.comehere.ssgserver.common.response.BaseResponseStatus;
import com.comehere.ssgserver.review.domain.Review;
import com.comehere.ssgserver.review.dto.req.ReviewCreateReqDTO;
import com.comehere.ssgserver.review.dto.req.ReviewImageReqDTO;
import com.comehere.ssgserver.review.dto.req.ReviewUpdateReqDTO;
import com.comehere.ssgserver.review.dto.resp.ReviewContentRespDTO;
import com.comehere.ssgserver.review.dto.resp.ReviewListRespDTO;
import com.comehere.ssgserver.review.infrastructure.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImp implements ReviewService {
	private final ReviewRepository reviewRepository;

	private final ReviewImageService reviewImageService;

	@Override
	@Transactional
	public void createReview(ReviewCreateReqDTO dto, UUID uuid) {
		if (reviewRepository.existsByPurchaseListId(dto.getPurchaseListId())) {
			throw new BaseException(BaseResponseStatus.REVIEW_ALREADY_WRITTEN);
		}

		Review review = Review.builder()
				.uuid(uuid)
				.itemCode(dto.getItemCode())
				.signinId(dto.getSigninId())
				.star(dto.getStar())
				.content(dto.getContent())
				.purchaseListId(dto.getPurchaseListId())
				.build();

		reviewRepository.save(review);

		createReviewImages(dto.getImages(), review);
	}

	@Override
	@Transactional
	public void updateReview(ReviewUpdateReqDTO dto, UUID uuid) {
		Review review = reviewRepository.findByUuidAndId(uuid, dto.getReviewId())
				.orElseThrow(() -> new BaseException(BaseResponseStatus.REVIEW_NOT_FOUND));

		reviewRepository.save(Review.builder()
				.id(review.getId())
				.signinId(review.getSigninId())
				.star(dto.getStar())
				.content(dto.getContent())
				.build());
	}

	@Override
	@Transactional
	public void deleteReview(Long reviewId, UUID uuid) {
		Review review = reviewRepository.findByUuidAndId(uuid, reviewId)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.REVIEW_NOT_FOUND));

		reviewImageService.deleteReviewImages(review);

		reviewRepository.delete(review);
	}

	@Override
	public ReviewListRespDTO getItemReviewList(String itemCode, Pageable page) {
		return ReviewListRespDTO.toBuild(reviewRepository.findByItemCode(itemCode, page));
	}

	@Override
	public ReviewContentRespDTO getReviewContent(Long reviewId) {
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.REVIEW_NOT_FOUND));

		return ReviewContentRespDTO.toBuild(review);
	}

	@Override
	public ReviewListRespDTO getMemberReviewList(UUID uuid, Pageable page) {
		return ReviewListRespDTO.toBuild(reviewRepository.findByUuid(uuid, page));
	}

	private void createReviewImages(List<ReviewImageReqDTO> dtos, Review review) {
		if (dtos.size() > 3) {
			throw new BaseException(BaseResponseStatus.REVIEW_IMAGE_LIMIT);
		}

		dtos.forEach(dto ->
				reviewImageService.createReviewImage(dto, review));
	}
}
