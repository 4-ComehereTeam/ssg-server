package com.comehere.ssgserver.clip.vo.req;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class CategoriesClipDeleteReqVO {
	private List<CategoryClipDeleteReqVO> categoryClipIds = new ArrayList<>();
}
