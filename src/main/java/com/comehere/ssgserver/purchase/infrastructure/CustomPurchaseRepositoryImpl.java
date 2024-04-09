package com.comehere.ssgserver.purchase.infrastructure;

import static com.comehere.ssgserver.purchase.domain.QPurchase.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.comehere.ssgserver.purchase.domain.PurchaseStatus;
import com.comehere.ssgserver.purchase.dto.req.NonPurchaseGetReqDTO;
import com.comehere.ssgserver.purchase.dto.req.PurchaseGetReqDTO;
import com.comehere.ssgserver.purchase.dto.resp.PurchaseGetRespDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
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
	public Optional<Long> findIdPurchaseIdBySenderNameAndSenderPhoneAndPurchaseCode(NonPurchaseGetReqDTO dto) {
		return Optional.ofNullable(queryFactory
				.select(purchase.id)
				.from(purchase)
				.where(purchase.senderName.eq(dto.getSenderName())
						.and(purchase.senderPhone.eq(dto.getSenderPhone()))
						.and(purchase.purchaseCode.eq(dto.getPurchaseCode())))
				.fetchOne());
	}

	@Override
	public List<PurchaseGetRespDTO> findPurchaseByUuidAndDate(UUID uuid, PurchaseGetReqDTO dto, Pageable page) {
		return queryFactory.select(Projections.fields(PurchaseGetRespDTO.class,
						purchase.id,
						purchase.purchaseCode
				))
				.from(purchase)
				.where(creatAtAfter(dto.getStartDate()),
						createAtBefore(dto.getEndDate()),
						checkAcceptedPurchaseStatus(dto.getAcceptedStatus()))
				.offset(page.getOffset())
				.limit(page.getPageSize())
				.orderBy(purchase.createAt.desc())
				.fetch();
	}

	private BooleanExpression creatAtAfter(String startDate) {
		return startDate == null ? null : purchase.createAt.after(LocalDate.parse(startDate).atStartOfDay());
	}

	private BooleanExpression createAtBefore(String endDate) {
		return endDate == null ? null : purchase.createAt.before(LocalDate.parse(endDate).atTime(LocalTime.MAX));
	}

	private BooleanExpression checkAcceptedPurchaseStatus(Boolean acceptedStatus) {
		return Boolean.TRUE.equals(acceptedStatus) ? purchase.status.eq(PurchaseStatus.ACCEPTED) : null;
	}
}
