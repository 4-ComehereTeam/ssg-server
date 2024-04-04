package com.comehere.ssgserver.purchase.application;

import com.comehere.ssgserver.purchase.dto.req.NonPurchaseGetReqDTO;
import com.comehere.ssgserver.purchase.dto.resp.NonPurchaseGetRespDTO;

public interface NonPurchaseService {
	void createNonPurchase();

	void deleteNonPurchase();

	NonPurchaseGetRespDTO getNonPurchase(NonPurchaseGetReqDTO dto);
}
