package com.comehere.ssgserver.item.infrastructual;

import static com.comehere.ssgserver.brand.domain.QBrandWithItem.*;
import static com.comehere.ssgserver.item.domain.QItemWithCategory.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import com.comehere.ssgserver.item.dto.req.ItemCountReqDTO;
import com.comehere.ssgserver.item.dto.req.ItemListReqDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomItemRepositoryImpl implements CustomItemRepository {
	private final JPAQueryFactory query;

	@Override
	public Slice<Long> getItemList(ItemListReqDTO dto, Pageable page) {
		List<Long> result = query.select(itemWithCategory.item.id)
				.from(itemWithCategory)
				.join(brandWithItem)
				.on(itemWithCategory.item.id.eq(brandWithItem.item.id))
				.where(
						bigCategoryEq(dto.getBigCategoryId()),
						middleCategoryEq(dto.getMiddleCategoryId()),
						smallCategoryEq(dto.getSmallCategoryId()),
						detailCategoryEq(dto.getDetailCategoryId()),
						brandIdEq(dto.getBrandId()),
						itemNameLike(dto.getSearch())
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

	@Override
	public Long getCount(ItemCountReqDTO dto) {
		return query.select(itemWithCategory.item.id.count())
				.from(itemWithCategory)
				.where(
						bigCategoryEq(dto.getBigCategoryId()),
						middleCategoryEq(dto.getMiddleCategoryId()),
						smallCategoryEq(dto.getSmallCategoryId())
				)
				.fetchOne();
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

	private BooleanExpression brandIdEq(Long brandId) {
		return brandId != null ? brandWithItem.brand.id.eq(brandId) : null;
	}

	private BooleanExpression itemNameLike(String[] itemName) {
		if(itemName == null || itemName.length == 0) {
			return null;
		}

		System.out.println(Arrays.toString(itemName));

		return Arrays.stream(itemName)
				.map(itemWithCategory.item.name::contains)
				.reduce(BooleanExpression::and)
				.orElse(null);
	}
}
