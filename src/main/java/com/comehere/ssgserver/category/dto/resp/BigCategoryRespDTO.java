package com.comehere.ssgserver.category.dto.resp;

import java.util.List;

import com.comehere.ssgserver.category.domain.BigCategory;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BigCategoryRespDTO {
	private int count;

	private List<BigCategory> bigCategories;
}
