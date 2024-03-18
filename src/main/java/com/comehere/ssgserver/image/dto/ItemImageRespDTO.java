package com.comehere.ssgserver.image.dto;

import java.util.ArrayList;
import java.util.List;

import com.comehere.ssgserver.image.domain.Image;
import com.comehere.ssgserver.image.domain.ItemWithImage;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemImageRespDTO {
	private Long itemId;
	private List<Image> images = new ArrayList<>();
}
