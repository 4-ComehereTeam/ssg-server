package com.comehere.ssgserver.purchase.application;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.comehere.ssgserver.common.exception.BaseException;
import com.comehere.ssgserver.common.response.BaseResponseStatus;
import com.comehere.ssgserver.purchase.dto.req.NonPurchaseGetReqDTO;
import com.comehere.ssgserver.purchase.dto.resp.NonPurchaseGetRespDTO;
import com.comehere.ssgserver.purchase.infrastructure.PurchaseListRepository;
import com.comehere.ssgserver.purchase.infrastructure.PurchaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NonPurchaseServiceImp implements NonPurchaseService {
	private final PurchaseRepository purchaseRepository;

	private final PurchaseListRepository purchaseListRepository;

	private final ModelMapper modelMapper;

	@Override
	public void createNonPurchase() {

	}

	@Override
	public void deleteNonPurchase() {

	}

	@Override
	public NonPurchaseGetRespDTO getNonPurchase(NonPurchaseGetReqDTO dto) {
		Long findPurchaseId = purchaseRepository.findPurchaseIdByNameAndPhoneAndPurchaseCode(dto)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.PURCHASE_NOT_FOUND));

		return new NonPurchaseGetRespDTO(purchaseListRepository.findPurchaseListByPurchaseId(findPurchaseId));

	}

}
