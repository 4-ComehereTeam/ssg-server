package com.comehere.ssgserver.item.dto.resp;

import java.util.List;

import com.comehere.ssgserver.item.domain.ItemImage;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemImageListRespDTO {
	private Long itemId;

	private List<ImageDTO> itemImages;
}
