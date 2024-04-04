package com.comehere.ssgserver.purchase.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.comehere.ssgserver.purchase.dto.req.NonPurchaseGetReqDTO;

public interface CustomPurchaseRepository {
	void updatePurchaseStatusToCancel(Long purchaseId);

	Optional<Long> findPurchaseIdByNameAndPhoneAndPurchaseCode(NonPurchaseGetReqDTO dto);

	List<Long> findPurchaseIdByUuid(UUID uuid);
}
