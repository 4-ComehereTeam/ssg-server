package com.comehere.ssgserver.review.application;

import com.comehere.ssgserver.image.vo.ReviewImageVO;
import com.comehere.ssgserver.review.dto.ReviewReqDTO;
import com.comehere.ssgserver.review.vo.ReviewUpdateVo;

public interface ReviewService {
	void createReview(ReviewReqDTO reviewReqDto);

	void deleteReview(Long reviewId);

	void updateReview(ReviewUpdateVo reviewUpdateVo);

	void updateReviewImage(ReviewImageVO reviewImageVO);
}
