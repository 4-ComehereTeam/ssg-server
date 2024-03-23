package com.comehere.ssgserver.image.vo;

import lombok.Getter;

@Getter
public class ReviewImageVO {
	private Long reviewImageId;
	private String imageUrl;
	private String alt;
	private Boolean thumbnail;
}
