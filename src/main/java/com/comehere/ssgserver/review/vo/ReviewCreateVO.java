package com.comehere.ssgserver.review.vo;

import java.util.List;

import com.comehere.ssgserver.image.dto.ImageReqDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewCreateVO {
	private Long memberId;

	private Long purchaseListId;

	private String itemCode;

	private Short star;

	private String content;

	private List<ImageReqDTO> images;
}
