package com.comehere.ssgserver.image.dto;

import com.comehere.ssgserver.image.domain.ItemImage;

import lombok.Getter;

@Getter
public class ImageDTO {
	private Long imageId;

	private String url;

	private String alt;

	private Boolean thumbnail;

	public ImageDTO(ItemImage ii) {
		this.imageId = ii.getId();
		this.url = ii.getUrl();
		this.alt = ii.getAlt();
		this.thumbnail = ii.getThumbnail() != 0;
	}
}
