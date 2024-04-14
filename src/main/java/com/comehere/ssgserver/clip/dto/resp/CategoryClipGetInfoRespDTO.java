package com.comehere.ssgserver.clip.dto.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryClipGetInfoRespDTO {
	private Long id;

	private Boolean isCliped;

	@Builder
	public CategoryClipGetInfoRespDTO(Long id, Boolean isCliped) {
		this.id = id;
		this.isCliped = isCliped;
	}
}
