package com.comehere.ssgserver.image.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemImageRespDTO {
	private Long itemId;

	private List<ImageDTO> itemImages;
}
