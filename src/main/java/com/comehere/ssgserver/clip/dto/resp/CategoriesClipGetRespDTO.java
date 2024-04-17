package com.comehere.ssgserver.clip.dto.resp;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoriesClipGetRespDTO {
	private List<CategoryClipGetRespDTO> categoryClip;

	@Builder
	public CategoriesClipGetRespDTO(List<CategoryClipGetRespDTO> categoryClip) {
		this.categoryClip = categoryClip;
	}
}
