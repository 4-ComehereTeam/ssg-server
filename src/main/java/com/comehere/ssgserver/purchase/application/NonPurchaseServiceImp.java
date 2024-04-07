package com.comehere.ssgserver.purchase.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.common.exception.BaseException;
import com.comehere.ssgserver.common.response.BaseResponseStatus;
import com.comehere.ssgserver.option.infrastructure.ItemOptionRepository;
import com.comehere.ssgserver.purchase.domain.Purchase;
import com.comehere.ssgserver.purchase.domain.PurchaseList;
import com.comehere.ssgserver.purchase.domain.PurchaseListStatus;
import com.comehere.ssgserver.purchase.dto.req.NonPurchaseDeleteReqDTO;
import com.comehere.ssgserver.purchase.dto.req.NonPurchaseGetReqDTO;
import com.comehere.ssgserver.purchase.dto.req.NonPurchaseListDeleteReqDTO;
import com.comehere.ssgserver.purchase.dto.resp.NonPurchaseGetRespDTO;
import com.comehere.ssgserver.purchase.infrastructure.PurchaseListRepository;
import com.comehere.ssgserver.purchase.infrastructure.PurchaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NonPurchaseServiceImp implements NonPurchaseService {
	private final PurchaseRepository purchaseRepository;

	private final PurchaseListRepository purchaseListRepository;

	private final ItemOptionRepository itemOptionRepository;

	@Override
	@Transactional
	public void deleteNonPurchaseList(NonPurchaseListDeleteReqDTO dto) {
		Purchase purchase = purchaseRepository.findByPurchaseCodeAndNameAndPhone(dto.getPurchaseCode(), dto.getName(),
						dto.getPhone())
				.orElseThrow(() -> new BaseException(BaseResponseStatus.PURCHASE_NOT_FOUND));


		PurchaseList purchaseList = purchaseListRepository.findByIdAndPurchaseId(dto.getPurchaseListId(),
						purchase.getId())
				.orElseThrow(() -> new BaseException(BaseResponseStatus.PURCHASE_LIST_NOT_FOUND));

		Long purchaseId = purchaseList.getPurchaseId();
		Long itemOptionId = purchaseList.getItemOptionId();
		Integer count = purchaseList.getCount();

		purchaseListRepository.save(PurchaseList.builder()
				.id(purchaseList.getId())
				.count(purchaseList.getCount())
				.cancelReason(dto.getCancelReason())
				.detailReason(dto.getDetailReason())
				.wroteReview(purchaseList.getWroteReview())
				.deleted(false)
				.status(PurchaseListStatus.CANCEL)
				.build());

		// 주문 취소 시 재고 복구
		restoreStockForPurchase(itemOptionId, count);


		if (purchaseListRepository.existsPurchaseCanceled(purchaseId)) {
			purchaseRepository.updatePurchaseStatusToCancel(purchaseId);
		}
	}

	@Override
	@Transactional
	public void deleteNonPurchase(NonPurchaseDeleteReqDTO dto) {
		Purchase purchase = purchaseRepository.findByPurchaseCodeAndNameAndPhone(dto.getPurchaseCode(), dto.getName(),
						dto.getPhone())
				.orElseThrow(() -> new BaseException(BaseResponseStatus.PURCHASE_NOT_FOUND));

		purchaseListRepository.deleteAllPurchaseList(purchase.getId());
		purchaseRepository.delete(purchase);
	}

	@Override
	public NonPurchaseGetRespDTO getNonPurchase(NonPurchaseGetReqDTO dto) {
		Long findPurchaseId = purchaseRepository.findPurchaseIdByNameAndPhoneAndPurchaseCode(dto)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.PURCHASE_NOT_FOUND));

		return NonPurchaseGetRespDTO.builder()
				.purchaseCode(dto.getPurchaseCode())
				.purchaseListIds(purchaseListRepository.findPurchaseListByPurchaseId(findPurchaseId))
				.build();
	}

	private void restoreStockForPurchase(Long itemOptionId, Integer count) {
		if (!itemOptionRepository.updateRestoreStock(itemOptionId, count)) {
			throw new BaseException(BaseResponseStatus.ITEM_STOCK_RESTORE_FAIL);
		}
	}
}
