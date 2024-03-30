package com.comehere.ssgserver.review.application;

import java.util.UUID;

import com.comehere.ssgserver.review.dto.req.ReviewCreateReqDTO;
import com.comehere.ssgserver.review.dto.req.ReviewUpdateReqDTO;
import com.comehere.ssgserver.review.vo.req.ReviewUpdateReqVo;

public interface ReviewService {
	void createReview(ReviewCreateReqDTO dto, UUID uuid);

	void updateReview(ReviewUpdateReqDTO dto, UUID uuid);

	void deleteReview(Long reviewId, UUID uuid);
}
