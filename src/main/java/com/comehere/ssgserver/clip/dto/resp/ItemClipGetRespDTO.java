package com.comehere.ssgserver.clip.dto.resp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemClipGetRespDTO {
	private Boolean isCliped;

	@Builder
	public ItemClipGetRespDTO(Boolean isCliped) {
		this.isCliped = isCliped;
	}
}
