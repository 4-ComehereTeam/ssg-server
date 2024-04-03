package com.comehere.ssgserver.cart.infrastructure;

import java.util.List;
import java.util.UUID;

import com.comehere.ssgserver.cart.domain.QCart;
import com.comehere.ssgserver.cart.dto.ItemCountDTO;
import com.comehere.ssgserver.cart.dto.request.ChangeCheckStateReqDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CustomCartRepositoryImpl implements CustomCartRepository {

	private final JPAQueryFactory query;

	@Override
	public List<ItemCountDTO> getCartId(UUID uuid) {

		QCart c = QCart.cart;

		return query
				.select(Projections.constructor(ItemCountDTO.class,
						c.itemOptionId,
						c.itemCount))
				.from(c)
				.where(c.uuid.eq(uuid))
				.fetch();
	}

	@Override
	public Long updateCheckState(UUID uuid, ChangeCheckStateReqDTO changeCheckStateReqDTO) {

		return query
				.update(QCart.cart)
				.set(QCart.cart.itemCheck,
						new CaseBuilder()
								.when(QCart.cart.itemCheck.eq(true))
								.then(false)
								.otherwise(true))
				.where(QCart.cart.uuid.eq(uuid),
						QCart.cart.itemOptionId.eq(changeCheckStateReqDTO.getItemOptionId()),
						QCart.cart.id.eq(changeCheckStateReqDTO.getCartId()))
				.execute();
	}
}
