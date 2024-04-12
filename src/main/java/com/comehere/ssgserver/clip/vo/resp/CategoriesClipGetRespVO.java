package com.comehere.ssgserver.clip.vo.resp;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class CategoriesClipGetRespVO {
	private List<CategoryClipGetRespVO> categoryClip = new ArrayList<>();
}
