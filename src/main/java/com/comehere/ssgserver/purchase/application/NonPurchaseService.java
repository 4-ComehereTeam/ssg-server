package com.comehere.ssgserver.purchase.application;

import com.comehere.ssgserver.purchase.dto.req.NonPurchaseDeleteReqDTO;
import com.comehere.ssgserver.purchase.dto.req.NonPurchaseGetReqDTO;
import com.comehere.ssgserver.purchase.dto.req.NonPurchaseListDeleteReqDTO;
import com.comehere.ssgserver.purchase.dto.resp.NonPurchaseGetRespDTO;

public interface NonPurchaseService {
	NonPurchaseGetRespDTO getNonPurchase(NonPurchaseGetReqDTO dto);

	void deleteNonPurchaseList(NonPurchaseListDeleteReqDTO dto);

	void deleteNonPurchase(NonPurchaseDeleteReqDTO dto);
}
