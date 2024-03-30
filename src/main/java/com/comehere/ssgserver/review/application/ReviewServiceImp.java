package com.comehere.ssgserver.review.application;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.review.domain.Review;
import com.comehere.ssgserver.review.dto.req.ReviewCreateReqDTO;
import com.comehere.ssgserver.review.dto.req.ReviewImageReqDTO;
import com.comehere.ssgserver.review.dto.req.ReviewUpdateReqDTO;
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
			throw new IllegalArgumentException("이미 리뷰를 작성한 구매목록입니다.");
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
				.orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));

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
				.orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));

		reviewImageService.deleteReviewImages(review);

		reviewRepository.delete(review);
	}

	private void createReviewImages(List<ReviewImageReqDTO> dtos, Review review) {
		if (dtos.size() > 3) {
			throw new IllegalArgumentException("리뷰 이미지는 최대 3개까지 등록 가능합니다.");
		}

		dtos.forEach(dto ->
				reviewImageService.createReviewImage(dto, review));
	}
}
