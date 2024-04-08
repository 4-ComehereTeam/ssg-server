package com.comehere.ssgserver.purchase.infrastructure;

import java.util.UUID;

public interface CustomAddressRepository {

	//기본 배송지 조회
	Long getDefaultAddress(UUID uuid);

	// uuid에 해당하는 기본 배송지 삭제
	Long cancelDefaultAddress(UUID uuid);

	//기본 배송지 변경
	Long updateDefaultAddress(UUID uuid, Long addressId);
}
