package com.comehere.ssgserver.review.presentation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.image.application.ImageService;
import com.comehere.ssgserver.image.vo.ReviewImageIdVo;
import com.comehere.ssgserver.image.vo.ReviewImageVO;
import com.comehere.ssgserver.image.vo.ReviewImagesVo;
import com.comehere.ssgserver.review.application.ReviewService;
import com.comehere.ssgserver.review.dto.ReviewReqDTO;
import com.comehere.ssgserver.review.vo.ReviewReqVO;
import com.comehere.ssgserver.review.vo.ReviewUpdateVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
@Tag(name = "review", description = "리뷰 컨트롤러")
public class ReviewController {
	private final ReviewService reviewService;

	private final ImageService imageService;

	@PostMapping
	@Operation(summary = "리뷰 등록")
	public void createReview(@RequestBody ReviewReqDTO reviewReqDto) {
		reviewService.createReview(reviewReqDto);
	}

	@DeleteMapping
	@Operation(summary = "리뷰 삭제")
	public void deleteReview(@RequestBody ReviewReqVO reviewReqVO) {
		reviewService.deleteReview(reviewReqVO.getReviewId());
	}

	@PutMapping
	@Operation(summary = "리뷰 수정")
	public void updateReview(@RequestBody ReviewUpdateVo reviewUpdateVo) {
		reviewService.updateReview(reviewUpdateVo);
	}

	@PutMapping("/image")
	@Operation(summary = "리뷰 이미지 수정")
	public void updateReviewImage(@RequestBody ReviewImageVO reviewImageVO) {
		imageService.updateReviewImage(reviewImageVO);
	}

	@PutMapping("/images")
	@Operation(summary = "리뷰 이미지 전체 수정")
	public void updateReviewImage(@RequestBody ReviewImagesVo reviewImagesVo) {
		reviewImagesVo.getReviewImageVO()
				.forEach(reviewImageVO -> imageService.updateReviewImage(reviewImageVO));
	}

	@DeleteMapping("/image")
	@Operation(summary = "리뷰 이미지 삭제")
	public void deleteReviewImage(@RequestBody ReviewImageIdVo reviewImageVO) {
		imageService.deleteReviewImage(reviewImageVO.getReviewImageId());
	}
}
