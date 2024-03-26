package com.comehere.ssgserver.image.dto;

import lombok.Getter;

@Getter
public class ThumbnailDTO {
	private Long itemId;

	private Long imageId;

	private String url;

	private String alt;
}
