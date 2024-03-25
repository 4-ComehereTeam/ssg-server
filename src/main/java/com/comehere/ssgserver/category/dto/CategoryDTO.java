package com.comehere.ssgserver.category.dto;

import com.comehere.ssgserver.category.domain.DetailCategory;
import com.comehere.ssgserver.category.domain.MiddleCategory;
import com.comehere.ssgserver.category.domain.SmallCategory;

import lombok.Getter;

@Getter
public class CategoryDTO {
	private Integer id;

	private String name;

	public CategoryDTO(MiddleCategory mc) {
		id = mc.getId();
		name = mc.getName();
	}

	public CategoryDTO(SmallCategory sc) {
		id = sc.getId();
		name = sc.getName();
	}

	public CategoryDTO(DetailCategory dc) {
		id = dc.getId();
		name = dc.getName();
	}
}
