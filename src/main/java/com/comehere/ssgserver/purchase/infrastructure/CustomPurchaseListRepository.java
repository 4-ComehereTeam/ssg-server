package com.comehere.ssgserver.purchase.infrastructure;

import java.util.List;

public interface CustomPurchaseListRepository {
	boolean existsPurchaseCanceled(Long purchaseId);

	void deleteAllPurchaseList(Long purchaseId);

	List<Long> findPurchaseListByPurchaseId(Long findPurchaseId);
}
