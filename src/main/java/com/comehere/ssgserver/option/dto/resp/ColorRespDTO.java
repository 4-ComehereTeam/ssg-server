package com.comehere.ssgserver.option.dto.resp;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
public class ColorRespDTO {
	private Long itemId;

	private List<ColorDTO> colors;
}
