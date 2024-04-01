package com.comehere.ssgserver.item.dto.resp;

import com.comehere.ssgserver.item.domain.ItemImage;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemThumbnailRespDTO {
	private Long itemId;

	private Long imageId;

	private String url;

	private String alt;

	public static ItemThumbnailRespDTO toBuild(ItemImage thumbnail) {
		return ItemThumbnailRespDTO.builder()
				.itemId(thumbnail.getItemId())
				.imageId(thumbnail.getId())
				.url(thumbnail.getImageUrl())
				.alt(thumbnail.getAlt())
				.build();
	}
}
