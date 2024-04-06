package com.comehere.ssgserver.purchase.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.comehere.ssgserver.purchase.dto.resp.PurchaseRespDTOByIdAndUuidDTO;

public interface CustomPurchaseListRepository {
	boolean existsPurchaseCanceled(Long purchaseId);

	void deleteAllPurchaseList(Long purchaseId);

	List<Long> findPurchaseListByPurchaseId(Long findPurchaseId);

	Optional<PurchaseRespDTOByIdAndUuidDTO> getRespDTOByIdAndUuid(Long purchaseListId, UUID uuid);
}
