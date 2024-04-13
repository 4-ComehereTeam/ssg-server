package com.comehere.ssgserver.purchase.infrastructure;

import static com.comehere.ssgserver.purchase.domain.QPurchase.*;
import static com.comehere.ssgserver.purchase.domain.QPurchaseList.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;

import com.comehere.ssgserver.purchase.domain.PurchaseStatus;
import com.comehere.ssgserver.purchase.dto.req.NonPurchaseGetReqDTO;
import com.comehere.ssgserver.purchase.dto.req.PurchaseGetReqDTO;
import com.comehere.ssgserver.purchase.dto.resp.PurchasesGetRespDTO;
import com.querydsl.core.Tuple;
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
	public List<PurchasesGetRespDTO> findPurchaseByUuidAndDate(UUID uuid, PurchaseGetReqDTO dto, Pageable page) {
		List<Tuple> result = queryFactory.select(purchase.purchaseCode, purchaseList.id)
				.from(purchase)
				.leftJoin(purchaseList).on(purchase.id.eq(purchaseList.purchaseId))
				.where(purchase.uuid.eq(uuid),
						creatAtAfter(dto.getStartDate()),
						createAtBefore(dto.getEndDate()),
						checkAcceptedPurchaseStatus(dto.getAcceptedStatus()))
				.orderBy(purchase.createAt.desc())
				.fetch();

		List<PurchasesGetRespDTO> respDTO = new ArrayList<>();

		// purchaseCode를 기준으로 purchaseListId를 그룹화
		result.stream()
				.collect(Collectors.groupingBy(tuple -> tuple.get(purchase.purchaseCode),
						LinkedHashMap::new,
						Collectors.mapping(tuple -> tuple.get(purchaseList.id), Collectors.toList())))
				.forEach((purchaseCode, purchaseListIds) -> {
					respDTO.add(PurchasesGetRespDTO.builder()
							.purchaseCode(purchaseCode)
							.purchaseListIds(purchaseListIds)
							.build());
				});

		return respDTO;
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
