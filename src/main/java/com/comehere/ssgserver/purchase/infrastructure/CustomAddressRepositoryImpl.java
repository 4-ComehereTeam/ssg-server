package com.comehere.ssgserver.purchase.infrastructure;

import java.util.UUID;

import com.comehere.ssgserver.purchase.domain.QAddress;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomAddressRepositoryImpl implements CustomAddressRepository {

	private final JPAQueryFactory query;

	@Override
	public Long getDefaultAddress(UUID uuid) {

		Long result = query.select(QAddress.address1.id)
				.from(QAddress.address1)
				.where(QAddress.address1.uuid.eq(uuid), QAddress.address1.defaultAddress.eq(true))
				.fetchOne();

		return result;
	}
}
