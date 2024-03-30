package com.comehere.ssgserver.category.vo.resp;

import com.comehere.ssgserver.category.domain.DetailCategory;
import com.comehere.ssgserver.category.domain.MiddleCategory;
import com.comehere.ssgserver.category.domain.SmallCategory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryVO {
	private Integer id;

	private String name;
}
