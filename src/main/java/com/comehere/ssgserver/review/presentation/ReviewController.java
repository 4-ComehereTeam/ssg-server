package com.comehere.ssgserver.review.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.common.security.jwt.JWTUtil;
import com.comehere.ssgserver.review.application.ReviewImageService;
import com.comehere.ssgserver.review.application.ReviewService;
import com.comehere.ssgserver.review.dto.req.ReviewCreateReqDTO;
import com.comehere.ssgserver.review.dto.req.ReviewImageCreateDTO;
import com.comehere.ssgserver.review.dto.req.ReviewImageUpdateReqDTO;
import com.comehere.ssgserver.review.dto.req.ReviewUpdateReqDTO;
import com.comehere.ssgserver.review.dto.resp.ReviewSummaryDTO;
import com.comehere.ssgserver.review.infrastructure.ReviewRepository;
import com.comehere.ssgserver.review.vo.req.ReviewCreateReqVO;
import com.comehere.ssgserver.review.vo.req.ReviewImageCreateReqVO;
import com.comehere.ssgserver.review.vo.req.ReviewImageUpdateReqVO;
import com.comehere.ssgserver.review.vo.req.ReviewUpdateReqVo;
import com.comehere.ssgserver.review.vo.resp.ReviewContentRespVO;
import com.comehere.ssgserver.review.vo.resp.ReviewImageListRespVO;
import com.comehere.ssgserver.review.vo.resp.ReviewImageRespVO;
import com.comehere.ssgserver.review.vo.resp.ReviewListRespVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
@Tag(name = "review", description = "리뷰 컨트롤러")
@Slf4j
public class ReviewController {
	private final ReviewService reviewService;

	private final ReviewImageService reviewImageService;

	private final ModelMapper modelMapper;

	private final JWTUtil jwtUtil;

	@PostMapping
	@Operation(summary = "리뷰 등록")
	public BaseResponse<?> createReview(@RequestHeader("Authorization") String authorization,
			@RequestBody ReviewCreateReqVO vo) {
		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);
		reviewService.createReview(modelMapper.map(vo, ReviewCreateReqDTO.class), uuid);
		return new BaseResponse<>();
	}

	@PutMapping
	@Operation(summary = "리뷰 수정")
	public BaseResponse<?> updateReview(@RequestBody ReviewUpdateReqVo vo,
			@RequestHeader("Authorization") String authorization) {
		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);

		reviewService.updateReview(modelMapper.map(vo, ReviewUpdateReqDTO.class), uuid);
		return new BaseResponse<>();
	}

	@DeleteMapping("/{reviewId}")
	@Operation(summary = "리뷰 삭제")
	public BaseResponse<?> deleteReview(
			@PathVariable("reviewId") Long reviewId,
			@RequestHeader("Authorization") String authorization) {

		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);
		reviewService.deleteReview(reviewId, uuid);

		return new BaseResponse<>();
	}

	@PostMapping("/images")
	@Operation(summary = "리뷰 이미지 등록")
	public BaseResponse<?> createReviewImage(
			@RequestBody ReviewImageCreateReqVO vo,
			@RequestHeader("Authorization") String authorization) {

		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);
		reviewImageService.createReviewImage(modelMapper.map(vo, ReviewImageCreateDTO.class), uuid);

		return new BaseResponse<>();
	}

	@PutMapping("/images")
	@Operation(summary = "리뷰 이미지 수정")
	public BaseResponse<?> updateReviewImage(
			@RequestBody List<ReviewImageUpdateReqVO> vo,
			@RequestHeader("Authorization") String authorization) {

		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);

		List<ReviewImageUpdateReqDTO> dto = new ArrayList<>();

		vo.forEach(reviewImageUpdateReqVO -> {
			dto.add(modelMapper.map(reviewImageUpdateReqVO, ReviewImageUpdateReqDTO.class));
		});

		reviewImageService.updateReviewImages(dto, uuid);

		return new BaseResponse<>();
	}

	@DeleteMapping("/images/{reviewImageId}")
	@Operation(summary = "리뷰 이미지 삭제")
	public BaseResponse<?> deleteReviewImage(
			@PathVariable("reviewImageId") Long reviewImageId,
			@RequestHeader("Authorization") String authorization) {

		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);
		reviewImageService.deleteReviewImage(reviewImageId, uuid);

		return new BaseResponse<>();
	}

	@GetMapping("/item/{itemCode}")
	@Operation(summary = "상품 별 리뷰 목록 조회")
	public BaseResponse<ReviewListRespVO> getItemReviewList(
			@PathVariable("itemCode") String itemCode,
			@PageableDefault(size = 5) Pageable page) {
		log.info("상품 리뷰 목록 조회 : itemCode={}", itemCode);
		return new BaseResponse<>(modelMapper.map(
				reviewService.getItemReviewList(itemCode, page), ReviewListRespVO.class));
	}

	@GetMapping("/content/{reviewId}")
	@Operation(summary = "리뷰 내용 조회")
	public BaseResponse<ReviewContentRespVO> getReviewContent(@PathVariable("reviewId") Long reviewId) {
		log.info("리뷰 내용 조회 : reviewId={}", reviewId);
		return new BaseResponse<>(modelMapper.map(
				reviewService.getReviewContent(reviewId), ReviewContentRespVO.class));
	}

	@GetMapping("/images/{reviewId}")
	@Operation(summary = "리뷰 별 이미지 조회")
	public BaseResponse<ReviewImageRespVO> getReviewImage(@PathVariable("reviewId") Long reviewId) {
		log.info("리뷰 이미지 조회 : reviewId={}", reviewId);
		return new BaseResponse<>(modelMapper.map(
				reviewImageService.getReviewImages(reviewId), ReviewImageRespVO.class));
	}

	@GetMapping("/member")
	@Operation(summary = "회원 별 리뷰 목록 조회")
	public BaseResponse<ReviewListRespVO> getMemberReviewList(
			@RequestHeader("Authorization") String accessToken,
			@PageableDefault(size = 5) Pageable page) {

		log.info("회원 리뷰 목록 조회");
		return new BaseResponse<>(modelMapper.map(
				reviewService.getMemberReviewList(jwtUtil.getUuidByAuthorization(accessToken), page),
				ReviewListRespVO.class));
	}

	@GetMapping("/images/item/{itemCode}")
	@Operation(summary = "상품 별 전체 리뷰 이미지 조회")
	public BaseResponse<ReviewImageListRespVO> getReviewImageList(
			@PathVariable("itemCode") String itemCode,
			@PageableDefault(size = 20) Pageable page) {
		log.info("상품 리뷰 이미지 전체 조회 : itemCode={}", itemCode);
		return new BaseResponse<>(modelMapper.map(
				reviewImageService.getReviewImageList(itemCode, page),
				ReviewImageListRespVO.class));
	}
}
