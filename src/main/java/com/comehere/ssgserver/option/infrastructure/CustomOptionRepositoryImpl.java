package com.comehere.ssgserver.option.infrastructure;

import static com.comehere.ssgserver.option.domain.QColor.*;
import static com.comehere.ssgserver.option.domain.QEtc.*;
import static com.comehere.ssgserver.option.domain.QItemOption.*;
import static com.comehere.ssgserver.option.domain.QSize.*;
import static com.comehere.ssgserver.purchase.domain.QPurchaseList.*;
import static com.comehere.ssgserver.review.domain.QReview.*;

import java.util.List;

import com.comehere.ssgserver.option.domain.ItemOption;
import com.comehere.ssgserver.option.dto.resp.ItemOptionInfoRespDTO;
import com.querydsl.core.types.Projections;
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

	@Override
	public Long findOptionId(Long reviewId) {
		return query.select(purchaseList.itemOptionId)
				.from(review)
				.join(purchaseList)
				.on(review.purchaseListId.eq(purchaseList.id))
				.where(review.id.eq(reviewId))
				.fetchOne();
	}

	@Override
	public ItemOptionInfoRespDTO getOptionInfo(Long optionId) {
		return query.select(Projections.constructor(ItemOptionInfoRespDTO.class,
					itemOption.id.as("optionId"),
					color.value.as("color"),
					size.value.as("size"),
					etc.value.as("etc")
				))
				.from(itemOption)
				.leftJoin(color).on(color.id.eq(itemOption.color.id))
				.leftJoin(size).on(size.id.eq(itemOption.size.id))
				.leftJoin(etc).on(etc.id.eq(itemOption.etc.id))
				.where(itemOption.id.eq(optionId))
				.fetchOne();
	}

	private BooleanExpression colorIdEq(Long colorId) {
		return colorId != null ? itemOption.color.id.eq(colorId) : null;
	}

	private BooleanExpression sizeIdEq(Long sizeId) {
		// QSize size = new QSize("size");
		return sizeId != null ? itemOption.size.id.eq(sizeId) : null;
	}
}
