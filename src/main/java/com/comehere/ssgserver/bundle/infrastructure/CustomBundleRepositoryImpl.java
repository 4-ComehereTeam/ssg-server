package com.comehere.ssgserver.bundle.infrastructure;

import static com.comehere.ssgserver.bundle.domain.QBundle.*;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQueryFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CustomBundleRepositoryImpl implements CustomBundleRepository {
	private final JPQLQueryFactory query;

	@Override
	public Slice<Long> getBundleList(Integer categoryId, Pageable page) {
		List<Long> result = query.select(bundle.id)
				.from(bundle)
				.where(categoryIdEq(categoryId))
				.limit(page.getPageSize() + 1)
				.offset(page.getOffset())
				.fetch();

		boolean hasNext = false;
		if(result.size() > page.getPageSize()) {
			result.remove(page.getPageSize());
			hasNext = true;
		}

		return new SliceImpl<>(result, page, hasNext);
	}

	private BooleanExpression categoryIdEq(Integer categoryId) {
		return categoryId != null ? bundle.categoryId.eq(categoryId) : null;
	}
}
