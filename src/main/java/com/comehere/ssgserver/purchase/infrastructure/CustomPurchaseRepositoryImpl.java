package com.comehere.ssgserver.purchase.infrastructure;

import static com.comehere.ssgserver.purchase.domain.QPurchase.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.comehere.ssgserver.purchase.domain.PurchaseStatus;
import com.comehere.ssgserver.purchase.dto.req.NonPurchaseGetReqDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomPurchaseRepositoryImpl implements CustomPurchaseRepository {
	private final JPAQueryFactory queryFactory;

	@Override
	public void updatePurchaseStatusToCancel(Long purchaseId) {
		queryFactory
				.update(purchase)
				.set(purchase.status, PurchaseStatus.CANCEL)
				.execute();
	}

	@Override
	public Optional<Long> findPurchaseIdByNameAndPhoneAndPurchaseCode(NonPurchaseGetReqDTO dto) {
		return Optional.ofNullable(queryFactory
				.select(purchase.id)
				.from(purchase)
				.where(purchase.name.eq(dto.getName())
						.and(purchase.phone.eq(dto.getPhone()))
						.and(purchase.purchaseCode.eq(dto.getPurchaseCode())))
				.fetchOne());
	}

	@Override
	public List<Long> findPurchaseIdByUuid(UUID uuid) {
		return queryFactory
				.select(purchase.id)
				.from(purchase)
				.where(purchase.uuid.eq(uuid))
				.fetch();
	}

}
