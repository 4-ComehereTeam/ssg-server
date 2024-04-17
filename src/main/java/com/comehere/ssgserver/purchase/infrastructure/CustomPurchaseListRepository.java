package com.comehere.ssgserver.purchase.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.comehere.ssgserver.purchase.dto.resp.PurchaseListByIdAndUuidRespDTO;

public interface CustomPurchaseListRepository {
	boolean existsPurchaseCanceled(Long purchaseId);

	void deleteAllPurchaseList(Long purchaseId);

	List<Long> findPurchaseListByPurchaseId(Long findPurchaseId);

	Optional<PurchaseListByIdAndUuidRespDTO> getRespDTOByIdAndUuid(Long purchaseListId, UUID uuid);

	List<Long> findIdsByPurchaseId(Long purchaseId);
}
