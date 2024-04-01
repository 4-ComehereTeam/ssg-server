package com.comehere.ssgserver.purchase.application;

import java.util.List;
import java.util.UUID;

import com.comehere.ssgserver.purchase.dto.req.PurchaseCreateReqDTO;
import com.comehere.ssgserver.purchase.dto.req.PurchaseListCreateReqDTO;
import com.comehere.ssgserver.purchase.dto.req.PurchaseListDeleteReqDTO;
import com.comehere.ssgserver.purchase.dto.resp.PurchasesGetRespDTO;

public interface PurchaseService {
	void createPurchase(PurchaseCreateReqDTO dto, UUID uuid);

	void createPurchaseList(PurchaseListCreateReqDTO dto, Long purchaseId);

	void deletePurchaseList(PurchaseListDeleteReqDTO dto, UUID uuid);

	List<PurchasesGetRespDTO> getPurchases(UUID uuid);
}
