package com.comehere.ssgserver.review.dto;

import java.util.List;

import com.comehere.ssgserver.image.dto.ImageReqDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewReqDTO {
	private Long memberId;

	private Long purchaseListId;

	private String itemCode;

	private Short star;

	private Short taste;

	private Short boxing;

	private Short life;

	private String content;

	private List<ImageReqDTO> images;
}
