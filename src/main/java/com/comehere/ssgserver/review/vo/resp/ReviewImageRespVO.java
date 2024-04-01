package com.comehere.ssgserver.review.vo.resp;

import java.util.List;

import com.comehere.ssgserver.review.dto.resp.ReviewImageDTO;

import lombok.Getter;

@Getter
public class ReviewImageRespVO {
	private Long reviewId;

	private List<ReviewImageDTO> images;
}
