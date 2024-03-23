package com.comehere.ssgserver.review.application;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.comehere.ssgserver.image.application.ImageService;
import com.comehere.ssgserver.image.domain.ReviewImage;
import com.comehere.ssgserver.image.dto.ImageReqDTO;
import com.comehere.ssgserver.image.infrastructure.ReviewImageRepository;
import com.comehere.ssgserver.member.infrastructure.MemberRepository;
import com.comehere.ssgserver.review.domain.Review;
import com.comehere.ssgserver.review.dto.ReviewReqDTO;
import com.comehere.ssgserver.review.infrastructure.ReviewRepository;
import com.comehere.ssgserver.review.vo.ReviewUpdateVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImp implements ReviewService {
	private final ReviewRepository reviewRepository;

	private final MemberRepository memberRepository;

	private final ReviewImageRepository reviewImageRepository;

	private final ImageService imageService;

	@Override
	public void createReview(ReviewReqDTO reviewReqDto) {
		reviewRepository.findByPurchaseListId(reviewReqDto.getPurchaseListId())
				.ifPresent(review -> {
					throw new IllegalArgumentException("이미 리뷰를 작성하셨습니다.");
				});

		Review review = Review.builder()
				.member(memberRepository.findById(reviewReqDto.getMemberId())
						.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다.")))
				.itemCode(reviewReqDto.getItemCode())
				.purchaseListId(reviewReqDto.getPurchaseListId())
				.star(reviewReqDto.getStar())
				.taste(reviewReqDto.getTaste())
				.boxing(reviewReqDto.getBoxing())
				.life(reviewReqDto.getLife())
				.content(reviewReqDto.getContent())
				.date(LocalDateTime.now())
				.build();

		reviewRepository.save(review);

		createReviewImages(reviewReqDto.getImages(), review);
	}

	@Override
	public void deleteReview(Long reviewId) {
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));

		// 리뷰의 모든 이미지 삭제
		imageService.deleteReviewImages(reviewId);

		reviewRepository.delete(review);
	}

	@Override
	public void updateReview(ReviewUpdateVo reviewUpdateVo) {
		Review review = reviewRepository.findById(reviewUpdateVo.getReviewId())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));

		review.updateReview(reviewUpdateVo);

		reviewRepository.save(review);
	}

	private void createReviewImages(List<ImageReqDTO> images, Review review) {
		images.forEach(image -> {
			ReviewImage reviewImage = ReviewImage.builder()
					.review(review)
					.imageUrl(image.getImageUrl())
					.alt(image.getAlt())
					.thumbnail(image.getThumbnail())
					.build();

			reviewImageRepository.save(reviewImage);
		});
	}
}
