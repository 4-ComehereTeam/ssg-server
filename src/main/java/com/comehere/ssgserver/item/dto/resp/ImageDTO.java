package com.comehere.ssgserver.item.dto.resp;

import com.comehere.ssgserver.item.domain.ItemImage;

import lombok.Getter;

@Getter
public class ImageDTO {
	private Long imageId;

	private String url;

	private String alt;

	private Boolean thumbnail;

	public ImageDTO(ItemImage ii) {
		this.imageId = ii.getId();
		this.url = ii.getImageUrl();
		this.alt = ii.getAlt();
		this.thumbnail = ii.getThumbnail();
	}
}
