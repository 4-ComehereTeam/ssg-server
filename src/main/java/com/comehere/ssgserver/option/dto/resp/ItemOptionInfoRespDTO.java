package com.comehere.ssgserver.option.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemOptionInfoRespDTO {
	private Long itemOptionId;

	private String color;

	private String size;

	private String etc;
}
