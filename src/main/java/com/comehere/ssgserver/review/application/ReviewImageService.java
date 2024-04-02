package com.comehere.ssgserver.review.application;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.comehere.ssgserver.review.domain.Review;
import com.comehere.ssgserver.review.dto.req.ReviewImageCreateDTO;
import com.comehere.ssgserver.review.dto.req.ReviewImageReqDTO;
import com.comehere.ssgserver.review.dto.req.ReviewImageUpdateReqDTO;
import com.comehere.ssgserver.review.dto.resp.ReviewImageListRespDTO;
import com.comehere.ssgserver.review.dto.resp.ReviewImageRespDTO;

public interface ReviewImageService {

	void createReviewImage(ReviewImageCreateDTO dto, UUID uuid);

	void updateReviewImages(List<ReviewImageUpdateReqDTO> dto, UUID uuid);

	void updateReviewImage(ReviewImageUpdateReqDTO dto, UUID uuid);

	void deleteReviewImage(Long reviewImageId, UUID uuid);

	void createReviewImage(ReviewImageReqDTO dto, Review review);

	void deleteReviewImages(Review review);

	ReviewImageRespDTO getReviewImages(Long reviewId);

	ReviewImageListRespDTO getReviewImageList(String itemCode, Pageable page);
}
