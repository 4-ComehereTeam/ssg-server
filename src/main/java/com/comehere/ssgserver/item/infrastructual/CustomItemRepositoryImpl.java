package com.comehere.ssgserver.item.infrastructual;

import static com.comehere.ssgserver.item.domain.QItemWithCategory.*;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import com.comehere.ssgserver.item.vo.ItemListReqVO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomItemRepositoryImpl implements CustomItemRepository {
	private final JPAQueryFactory query;

	@Override
	public Slice<Long> getItemList(ItemListReqVO vo, Pageable page) {
		List<Long> result = query.select(itemWithCategory.item.id)
				.from(itemWithCategory)
				.where(
						bigCategoryEq(vo.getBigCategoryId()),
						middleCategoryEq(vo.getMiddleCategoryId()),
						smallCategoryEq(vo.getSmallCategoryId()),
						detailCategoryEq(vo.getDetailCategoryId())
				)
				.offset(page.getOffset())
				.limit(page.getPageSize() + 1)
				.fetch();

		boolean hasNext = false;
		if(result.size() > page.getPageSize()) {
			result.remove(page.getPageSize());
			hasNext = true;
		}

		return new SliceImpl<>(result, page, hasNext);
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

	private BooleanExpression detailCategoryEq(Integer detailCategoryId) {
		return detailCategoryId != null ? itemWithCategory.detailCategory.eq(detailCategoryId) : null;
	}
}
