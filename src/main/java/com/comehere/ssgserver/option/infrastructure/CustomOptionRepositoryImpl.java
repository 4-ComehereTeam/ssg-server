package com.comehere.ssgserver.option.infrastructure;

import static com.comehere.ssgserver.option.domain.QColor.*;
import static com.comehere.ssgserver.option.domain.QEtc.*;
import static com.comehere.ssgserver.option.domain.QItemOption.*;
import static com.comehere.ssgserver.option.domain.QSize.*;
import static com.comehere.ssgserver.purchase.domain.QPurchaseList.*;
import static com.comehere.ssgserver.review.domain.QReview.*;

import java.util.List;
import java.util.Optional;

import com.comehere.ssgserver.option.domain.ItemOption;
import com.comehere.ssgserver.option.dto.resp.ColorDTO;
import com.comehere.ssgserver.option.dto.resp.ItemOptionInfoRespDTO;
import com.comehere.ssgserver.option.dto.resp.SizeDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomOptionRepositoryImpl implements CustomOptionRepository {
	private final JPAQueryFactory query;

	@Override
	public List<ColorDTO> findColor(Long itemId) {
		return query.select(Projections.constructor(ColorDTO.class,
					itemOption.id.min().as("optionId"),
					color.id.as("colorId"),
					color.value.as("value"),
					itemOption.stock.sum().as("stock")
				))
				.from(itemOption)
				.leftJoin(itemOption.color, color)
				.where(itemOption.item.id.eq(itemId))
				.groupBy(color.id)
				.fetch();
	}

	@Override
	public List<SizeDTO> findSize(Long itemId, Long colorId) {
		return query.select(Projections.constructor(SizeDTO.class,
					itemOption.id.min().as("optionId"),
					size.id.as("sizeId"),
					size.value.as("value"),
					itemOption.stock.sum    ().as("stock")
				))
				.from(itemOption)
				.leftJoin(itemOption.size, size)
				.where(
						itemOption.item.id.eq(itemId),
						colorIdEq(colorId)
				)
				.groupBy(size.id)
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

	@Override
	public Optional<Long> getItemIdById(Long optionId) {
		return Optional.ofNullable(query.select(itemOption.item.id)
				.from(itemOption)
				.where(itemOption.id.eq(optionId))
				.fetchOne());
	}

	private BooleanExpression colorIdEq(Long colorId) {
		return colorId != null ? itemOption.color.id.eq(colorId) : null;
	}

	private BooleanExpression sizeIdEq(Long sizeId) {
		// QSize size = new QSize("size");
		return sizeId != null ? itemOption.size.id.eq(sizeId) : null;
	}
}
