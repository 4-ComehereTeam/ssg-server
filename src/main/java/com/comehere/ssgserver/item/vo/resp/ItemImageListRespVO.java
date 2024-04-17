package com.comehere.ssgserver.item.vo.resp;

import java.util.List;

import com.comehere.ssgserver.item.dto.resp.ImageDTO;

import lombok.Getter;

@Getter
public class ItemImageListRespVO {
	private Long itemId;

	private List<ImageDTO> itemImages;
}
