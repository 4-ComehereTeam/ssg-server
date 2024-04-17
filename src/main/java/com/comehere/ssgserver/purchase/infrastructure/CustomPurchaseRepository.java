package com.comehere.ssgserver.purchase.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.comehere.ssgserver.purchase.dto.req.NonPurchaseGetReqDTO;
import com.comehere.ssgserver.purchase.dto.req.PurchaseGetReqDTO;
import com.comehere.ssgserver.purchase.dto.resp.PurchasesGetRespDTO;

public interface CustomPurchaseRepository {
	void updatePurchaseStatusToCancel(Long purchaseId);

	Optional<Long> findIdPurchaseIdBySenderNameAndSenderPhoneAndPurchaseCode(NonPurchaseGetReqDTO dto);

	List<PurchasesGetRespDTO> findPurchaseByUuidAndDate(UUID uuid, PurchaseGetReqDTO dto, Pageable page);

}
