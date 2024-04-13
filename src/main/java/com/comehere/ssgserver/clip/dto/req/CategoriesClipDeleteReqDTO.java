package com.comehere.ssgserver.clip.dto.req;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class CategoriesClipDeleteReqDTO {
	private List<CategoryClipDeleteReqDTO> categoryClipIds = new ArrayList<>();
}
