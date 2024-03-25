package com.comehere.ssgserver.image.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemThumbnailRespDTO {
	private Long itemId;

	private Long imageId;

	private String url;

	private String alt;
}
