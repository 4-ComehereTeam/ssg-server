package com.comehere.ssgserver.category.dto;

import com.comehere.ssgserver.category.domain.MiddleCategory;

import lombok.Getter;

@Getter
public class MiddleCategoryDTO {
	private Integer middleCategoryId;
	private String name;

	public MiddleCategoryDTO(MiddleCategory mc) {
		middleCategoryId = mc.getId();
		name = mc.getName();
	}
}
