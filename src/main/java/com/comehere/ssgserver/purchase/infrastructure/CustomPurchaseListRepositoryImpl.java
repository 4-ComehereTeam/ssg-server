package com.comehere.ssgserver.purchase.infrastructure;

import static com.comehere.ssgserver.purchase.domain.QPurchaseList.*;

import java.util.List;

import com.comehere.ssgserver.purchase.domain.PurchaseListStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomPurchaseListRepositoryImpl implements CustomPurchaseListRepository {
	private final JPAQueryFactory queryFactory;

	@Override
	public boolean existsPurchaseCanceled(Long purchaseId) {
		return queryFactory
				.selectFrom(purchaseList)
				.where((purchaseList.status.ne(PurchaseListStatus.CANCEL)).and(purchaseList.purchaseId.eq(purchaseId)))
				.fetch()
				.isEmpty();
	}

	@Override
	public void deleteAllPurchaseList(Long purchaseId) {
		queryFactory
				.update(purchaseList)
				.set(purchaseList.deleted, true)
				.where(purchaseList.purchaseId.eq(purchaseId))
				.execute();
	}

	@Override
	public List<Long> findPurchaseListByPurchaseId(Long findPurchaseId) {
		return queryFactory
				.select(purchaseList.id)
				.from(purchaseList)
				.where(purchaseList.purchaseId.eq(findPurchaseId))
				.fetch();
	}
}
