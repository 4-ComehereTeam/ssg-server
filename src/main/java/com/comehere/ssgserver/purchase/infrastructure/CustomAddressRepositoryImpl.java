package com.comehere.ssgserver.purchase.infrastructure;

import static com.comehere.ssgserver.purchase.domain.QAddress.*;

import java.util.UUID;

import com.comehere.ssgserver.purchase.dto.req.ModifyRequestMessageReqDTO;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomAddressRepositoryImpl implements CustomAddressRepository {

	private final JPAQueryFactory query;

	// 기본 배송지 조회
	@Override
	public Long getDefaultAddress(UUID uuid) {

		return query.select(address1.id)
				.from(address1)
				.where(address1.uuid.eq(uuid), address1.defaultAddress.eq(true))
				.fetchOne();
	}

	// uuid에 해당하는 기본 배송지 삭제
	@Override
	public Long cancelDefaultAddress(UUID uuid) {

		return query.update(address1)
				.set(address1.defaultAddress, false)
				.where(address1.uuid.eq(uuid), address1.defaultAddress.eq(true))
				.execute();
	}

	// 기본 배송지 변경
	@Override
	public Long updateDefaultAddress(UUID uuid, Long addressId) {

		return query.update(address1)
				.set(address1.defaultAddress,
						new CaseBuilder()
								.when(address1.defaultAddress.eq(true))
								.then(false)
								.otherwise(true))
				.where(address1.uuid.eq(uuid),
						address1.id.eq(addressId))
				.execute();
	}

	@Override
	public Long updateRequestMessage(UUID uuid, ModifyRequestMessageReqDTO modifyRequestMessageReqDTO) {

		return query.update(address1)
				.set(address1.requestMessage, modifyRequestMessageReqDTO.getNewMessage())
				.where(address1.uuid.eq(uuid),
						address1.id.eq(modifyRequestMessageReqDTO.getAddressId()))
				.execute();
	}
}
