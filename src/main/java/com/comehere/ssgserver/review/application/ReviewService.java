package com.comehere.ssgserver.review.application;

import com.comehere.ssgserver.review.vo.ReviewCreateVO;
import com.comehere.ssgserver.review.vo.ReviewUpdateVo;

public interface ReviewService {
	void createReview(ReviewCreateVO reviewCreateVO);

	void deleteReview(Long reviewId);

	void updateReview(ReviewUpdateVo reviewUpdateVo);
}
