package com.comehere.ssgserver.review.vo.resp;

import java.util.List;

import com.comehere.ssgserver.review.dto.resp.ReviewImageListDTO;

import lombok.Getter;

@Getter
public class ReviewImageListRespVO {
	private String itemCode;

	private List<ReviewImageListDTO> images;

	private Integer currentPage;

	private Boolean hasNext;
}
