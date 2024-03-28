package com.comehere.ssgserver.option.infrastructure;

import static com.comehere.ssgserver.option.domain.QColor.*;
import static com.comehere.ssgserver.option.domain.QEtc.*;
import static com.comehere.ssgserver.option.domain.QItemOption.*;
import static com.comehere.ssgserver.option.domain.QSize.*;

import java.util.List;

import com.comehere.ssgserver.option.domain.ItemOption;
import com.comehere.ssgserver.option.domain.QSize;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomOptionRepositoryImpl implements CustomOptionRepository {
	private final JPAQueryFactory query;

	@Override
	public List<ItemOption> findSize(Long itemId, Long colorId) {
		return query.selectFrom(itemOption)
				.leftJoin(itemOption.size, size)
				.fetchJoin()
				.where(
						itemOption.item.id.eq(itemId),
						colorIdEq(colorId)
				)
				.fetch();
	}

	@Override
	public List<ItemOption> findEtc(Long itemId, Long colorId, Long sizeId) {
		return query.selectFrom(itemOption)
				.leftJoin(itemOption.etc, etc)
				.fetchJoin()
				.where(
						itemOption.item.id.eq(itemId),
						colorIdEq(colorId),
						sizeIdEq(sizeId)
				)
				.fetch();
	}

	private BooleanExpression colorIdEq(Long colorId) {
		return colorId != null ? itemOption.color.id.eq(colorId) : null;
	}

	private BooleanExpression sizeIdEq(Long sizeId) {
		// QSize size = new QSize("size");
		return sizeId != null ? itemOption.size.id.eq(sizeId) : null;
	}
}
