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
import com.comehere.ssgserver.review.dto.resp.ReviewImageListDTO;
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

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
@Tag(name = "review", description = "리뷰 컨트롤러")
public class ReviewController {
	private final ReviewService reviewService;

	private final ReviewImageService reviewImageService;

	private final ModelMapper modelMapper;

	private final JWTUtil jwtUtil;

	@PostMapping
	@Operation(summary = "리뷰 등록")
	public void createReview(@RequestHeader("Authorization") String authorization, @RequestBody ReviewCreateReqVO vo) {
		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);

		reviewService.createReview(modelMapper.map(vo, ReviewCreateReqDTO.class), uuid);
	}

	@PutMapping
	@Operation(summary = "리뷰 수정")
	public void updateReview(@RequestBody ReviewUpdateReqVo vo, @RequestHeader("Authorization") String authorization) {
		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);

		reviewService.updateReview(modelMapper.map(vo, ReviewUpdateReqDTO.class), uuid);
	}

	@DeleteMapping("/{reviewId}")
	@Operation(summary = "리뷰 삭제")
	public void deleteReview(
			@PathVariable("reviewId") Long reviewId,
			@RequestHeader("Authorization") String authorization) {
		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);

		System.out.println("reviewId = " + reviewId);
		System.out.println("uuid = " + uuid);
		reviewService.deleteReview(reviewId, uuid);
	}

	@PostMapping("/images")
	@Operation(summary = "리뷰 이미지 등록")
	public void createReviewImage(
			@RequestBody ReviewImageCreateReqVO vo,
			@RequestHeader("Authorization") String authorization) {

		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);
		reviewImageService.createReviewImage(modelMapper.map(vo, ReviewImageCreateDTO.class), uuid);
	}

	@PutMapping("/images")
	@Operation(summary = "리뷰 이미지 수정")
	public void updateReviewImage(
			@RequestBody List<ReviewImageUpdateReqVO> vo,
			@RequestHeader("Authorization") String authorization) {

		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);

		List<ReviewImageUpdateReqDTO> dto = new ArrayList<>();

		vo.forEach(reviewImageUpdateReqVO -> {
			dto.add(modelMapper.map(reviewImageUpdateReqVO, ReviewImageUpdateReqDTO.class));
		});

		reviewImageService.updateReviewImages(dto, uuid);
	}

	@DeleteMapping("/images/{reviewImageId}")
	@Operation(summary = "리뷰 이미지 삭제")
	public void deleteReviewImage(
			@PathVariable("reviewImageId") Long reviewImageId,
			@RequestHeader("Authorization") String authorization) {

		UUID uuid = jwtUtil.getUuidByAuthorization(authorization);
		reviewImageService.deleteReviewImage(reviewImageId, uuid);
	}

	@GetMapping("/item/{itemCode}")
	@Operation(summary = "상품 별 리뷰 목록 조회")
	public BaseResponse<ReviewListRespVO> getItemReviewList(
			@PathVariable("itemCode") String itemCode,
			@PageableDefault(size = 5) Pageable page) {
		return new BaseResponse<>(modelMapper.map(
				reviewService.getItemReviewList(itemCode, page), ReviewListRespVO.class));
	}

	@GetMapping("/content/{reviewId}")
	@Operation(summary = "리뷰 내용 조회")
	public BaseResponse<ReviewContentRespVO> getReviewContent(@PathVariable("reviewId") Long reviewId) {
		return new BaseResponse<>(modelMapper.map(
				reviewService.getReviewContent(reviewId), ReviewContentRespVO.class));
	}

	@GetMapping("/images/{reviewId}")
	@Operation(summary = "리뷰 별 이미지 조회")
	public BaseResponse<ReviewImageRespVO> getReviewImage(@PathVariable("reviewId") Long reviewId) {
		return new BaseResponse<>(modelMapper.map(
				reviewImageService.getReviewImages(reviewId), ReviewImageRespVO.class));
	}

	@GetMapping("/member")
	@Operation(summary = "회원 별 리뷰 목록 조회")
	public BaseResponse<ReviewListRespVO> getMemberReviewList(
			@RequestHeader("Authorization") String accessToken,
			@PageableDefault(size = 5) Pageable page) {

		return new BaseResponse<>(modelMapper.map(
				reviewService.getMemberReviewList(jwtUtil.getUuidByAuthorization(accessToken), page),
				ReviewListRespVO.class));
	}

	@GetMapping("/images/item/{itemCode}")
	@Operation(summary = "상품 별 전체 리뷰 이미지 조회")
	public BaseResponse<ReviewImageListRespVO> getReviewImageList(
			@PathVariable("itemCode") String itemCode,
			@PageableDefault(size = 5) Pageable page) {
		return new BaseResponse<>(modelMapper.map(
				reviewImageService.getReviewImageList(itemCode, page),
				ReviewImageListRespVO.class));
	}
}
