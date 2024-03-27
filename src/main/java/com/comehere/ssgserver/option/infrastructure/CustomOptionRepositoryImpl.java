package com.comehere.ssgserver.option.infrastructure;

import static com.comehere.ssgserver.option.domain.QColor.*;
import static com.comehere.ssgserver.option.domain.QItemOption.*;
import static com.comehere.ssgserver.option.domain.QSize.*;

import java.util.List;

import com.comehere.ssgserver.option.domain.ItemOption;
import com.comehere.ssgserver.option.domain.QColor;
import com.comehere.ssgserver.option.domain.QItemOption;
import com.comehere.ssgserver.option.domain.QSize;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomOptionRepositoryImpl implements CustomOptionRepository {
	private final JPAQueryFactory query;

	@Override
	public List<ItemOption> findSize(Long itemId, Long colorId) {
		return query.select(itemOption)
				.from(itemOption)
				.leftJoin(itemOption.size, size)
				.fetchJoin()
				.where(
						itemOption.item.id.eq(itemId),
						colorIdEq(colorId)
				)
				.fetch();
	}

	private BooleanExpression colorIdEq(Long colorId) {
		return colorId != null ? color.id.eq(colorId) : null;
	}
}
