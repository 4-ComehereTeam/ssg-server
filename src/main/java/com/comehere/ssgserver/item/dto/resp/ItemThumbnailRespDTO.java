package com.comehere.ssgserver.item.dto.resp;

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
