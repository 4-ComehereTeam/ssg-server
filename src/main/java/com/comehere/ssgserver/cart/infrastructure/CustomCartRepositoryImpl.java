package com.comehere.ssgserver.cart.infrastructure;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.comehere.ssgserver.cart.domain.QCart;
import com.comehere.ssgserver.cart.dto.ItemCountDTO;
import com.comehere.ssgserver.cart.dto.req.ChangeStateReqDTO;
import com.comehere.ssgserver.cart.dto.req.DeleteItemReqDTO;
import com.comehere.ssgserver.cart.dto.req.ItemQuantityModifyReqDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CustomCartRepositoryImpl implements CustomCartRepository {

	private final JPAQueryFactory query;

	// 장바구니에 담긴 상품 리스트 조회
	@Override
	public List<ItemCountDTO> getCartId(UUID uuid, Pageable pageable) {

		return query
				.select(Projections.constructor(ItemCountDTO.class,
						QCart.cart.itemOptionId,
						QCart.cart.itemCount))
				.from(QCart.cart)
				.where(QCart.cart.uuid.eq(uuid))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
	}

	// 상품 수량 변경
	@Override
	public Long updateItemQuantity(UUID uuid, Long ItemOptionId, int count) {
		return query
				.update(QCart.cart)
				.set(QCart.cart.itemCount, QCart.cart.itemCount.add(count))
				.where(QCart.cart.uuid.eq(uuid),
						QCart.cart.itemOptionId.eq(ItemOptionId))
				.execute();
	}

	// 상품 수량 감소
	@Override
	public Long minusItemQuantity(UUID uuid, ItemQuantityModifyReqDTO itemQuantityModifyReqDTO) {

		return query
				.update(QCart.cart)
				.set(QCart.cart.itemCount, QCart.cart.itemCount.subtract(1))
				.where(QCart.cart.uuid.eq(uuid),
						QCart.cart.itemOptionId.eq(itemQuantityModifyReqDTO.getItemOptionId()))
				.execute();
	}

	// 상품 체크 상태 변경
	@Override
	public Long updateCheckState(UUID uuid, ChangeStateReqDTO changeStateReqDTO) {

		return query
				.update(QCart.cart)
				.set(QCart.cart.itemCheck,
						new CaseBuilder()
								.when(QCart.cart.itemCheck.eq(true))
								.then(false)
								.otherwise(true))
				.where(QCart.cart.uuid.eq(uuid),
						QCart.cart.itemOptionId.eq(changeStateReqDTO.getItemOptionId()),
						QCart.cart.id.eq(changeStateReqDTO.getCartId()))
				.execute();
	}

	// 상품 핀 상태 변경
	@Override
	public Long updatePinState(UUID uuid, ChangeStateReqDTO changeStateReqDTO) {

		return query
				.update(QCart.cart)
				.set(QCart.cart.pinStatus,
						new CaseBuilder()
								.when(QCart.cart.pinStatus.eq(true))
								.then(false)
								.otherwise(true))
				.where(QCart.cart.uuid.eq(uuid),
						QCart.cart.itemOptionId.eq(changeStateReqDTO.getItemOptionId()),
						QCart.cart.id.eq(changeStateReqDTO.getCartId()))
				.execute();
	}

	@Override
	public Long deleteItem(UUID uuid, DeleteItemReqDTO deleteItemReqDTO) {

		return query
				.delete(QCart.cart)
				.where(QCart.cart.uuid.eq(uuid),
						QCart.cart.itemOptionId.in(deleteItemReqDTO.getItemOptionIds()))
				.execute();
	}
}
