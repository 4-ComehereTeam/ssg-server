package com.comehere.ssgserver.category.vo;

import java.util.List;

import com.comehere.ssgserver.category.domain.BigCategory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BigCategoryRespVO {
	private int count;

	private List<BigCategory> bigCategories;
}
