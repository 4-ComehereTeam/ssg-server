package com.comehere.ssgserver.item.infrastructual;

import static com.comehere.ssgserver.item.domain.QItem.*;
import static com.comehere.ssgserver.item.domain.QItemWithCategory.*;

import java.util.List;

import org.springframework.util.StringUtils;

import com.comehere.ssgserver.item.vo.ItemListReqVO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomItemRepositoryImpl implements CustomItemRepository {
	private final JPAQueryFactory query;

	@Override
	public List<Long> getItemList(ItemListReqVO vo) {
		return query.select(itemWithCategory.item.id)
				.from(itemWithCategory)
				.where(
						itemNameLike(vo.getSearch()),
						bigCategoryEq(vo.getBigCategoryId()),
						middleCategoryEq(vo.getMiddleCategoryId()),
						smallCategoryEq(vo.getSmallCategoryId())
				)
				.fetch();
	}

	private BooleanExpression itemNameLike(String itemName) {
		return StringUtils.hasText(itemName) ? item.name.like('%' + itemName + '%') : null;
	}

	private BooleanExpression bigCategoryEq(Integer bigCategoryId) {
		return bigCategoryId != null ? itemWithCategory.bigCategory.eq(bigCategoryId) : null;
	}

	private BooleanExpression middleCategoryEq(Integer middleCategoryId) {
		return middleCategoryId != null ? itemWithCategory.middleCategory.eq(middleCategoryId) : null;
	}

	private BooleanExpression smallCategoryEq(Integer smallCategoryId) {
		return smallCategoryId != null ? itemWithCategory.smallCategory.eq(smallCategoryId) : null;
	}
}
