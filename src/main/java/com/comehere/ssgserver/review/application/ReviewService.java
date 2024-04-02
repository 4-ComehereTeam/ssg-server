package com.comehere.ssgserver.review.application;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.comehere.ssgserver.review.dto.req.ReviewCreateReqDTO;
import com.comehere.ssgserver.review.dto.req.ReviewUpdateReqDTO;
import com.comehere.ssgserver.review.dto.resp.ReviewContentRespDTO;
import com.comehere.ssgserver.review.dto.resp.ReviewListRespDTO;

public interface ReviewService {
	void createReview(ReviewCreateReqDTO dto, UUID uuid);

	void updateReview(ReviewUpdateReqDTO dto, UUID uuid);

	void deleteReview(Long reviewId, UUID uuid);

	ReviewListRespDTO getItemReviewList(String itemCode, Pageable page);

	ReviewContentRespDTO getReviewContent(Long reviewId);

	ReviewListRespDTO getMemberReviewList(UUID uuid, Pageable page);
}
