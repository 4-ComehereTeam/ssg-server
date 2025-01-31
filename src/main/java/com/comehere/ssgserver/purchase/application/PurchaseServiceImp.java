package com.comehere.ssgserver.purchase.application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.common.exception.BaseException;
import com.comehere.ssgserver.common.response.BaseResponseStatus;
import com.comehere.ssgserver.item.domain.Item;
import com.comehere.ssgserver.option.infrastructure.ItemOptionRepository;
import com.comehere.ssgserver.purchase.domain.Purchase;
import com.comehere.ssgserver.purchase.domain.PurchaseList;
import com.comehere.ssgserver.purchase.domain.PurchaseListStatus;
import com.comehere.ssgserver.purchase.domain.PurchaseStatus;
import com.comehere.ssgserver.purchase.dto.req.PurchaseCreateReqDTO;
import com.comehere.ssgserver.purchase.dto.req.PurchaseGetReqDTO;
import com.comehere.ssgserver.purchase.dto.req.PurchaseListCreateReqDTO;
import com.comehere.ssgserver.purchase.dto.req.PurchaseListDeleteReqDTO;
import com.comehere.ssgserver.purchase.dto.resp.PurchaseCreateRespDTO;
import com.comehere.ssgserver.purchase.dto.resp.PurchaseListByIdAndUuidRespDTO;
import com.comehere.ssgserver.purchase.dto.resp.PurchaseListGetRespDTO;
import com.comehere.ssgserver.purchase.dto.resp.PurchasesGetRespDTO;
import com.comehere.ssgserver.purchase.infrastructure.PurchaseListRepository;
import com.comehere.ssgserver.purchase.infrastructure.PurchaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImp implements PurchaseService {
	private final PurchaseRepository purchaseRepository;

	private final PurchaseListRepository purchaseListRepository;

	private final ItemOptionRepository itemOptionRepository;

	@Override
	@Transactional
	public PurchaseCreateRespDTO createPurchase(PurchaseCreateReqDTO dto, UUID uuid) {
		Purchase purchase = Purchase.builder()
				.purchaseCode(makePurchaseCode())
				.uuid(uuid)
				.senderName(dto.getSenderName())
				.senderPhone(dto.getSenderPhone())
				.senderEmail(dto.getSenderEmail())
				.recipientName(dto.getRecipientName())
				.recipientPhone(dto.getRecipientPhone())
				.recipientEmail(dto.getRecipientEmail())
				.addressNickname(dto.getAddressNickname())
				.address(dto.getAddress())
				.detailAddress(dto.getDetailAddress())
				.zipcode(dto.getZipcode())
				.requestMessage(dto.getRequestMessage())
				.status(PurchaseStatus.ACCEPTED)
				.deleted(false)
				.build();

		purchaseRepository.save(purchase);

		dto.getPurchases()
				.forEach(purchaseListCreateReqDTO -> createPurchaseList(purchaseListCreateReqDTO, purchase.getId()));

		return PurchaseCreateRespDTO.builder()
				.purchaseCode(purchase.getPurchaseCode())
				.build();
	}

	@Override
	@Transactional
	public void createPurchaseList(PurchaseListCreateReqDTO dto, Long purchaseId) {
		if (purchaseListRepository.existsByPurchaseIdAndItemOptionId(purchaseId, dto.getItemOptionId())) {
			throw new BaseException(BaseResponseStatus.PURCHASE_LIST_DUPLICATE);
		}

		Item item = itemOptionRepository.getItemById(dto.getItemOptionId())
				.orElseThrow(() -> new BaseException(BaseResponseStatus.ITEM_OPTION_NOT_FOUND));

		purchaseListRepository.save(PurchaseList.builder()
				.purchaseId(purchaseId)
				.itemOptionId(dto.getItemOptionId())
				.itemName(item.getName())
				.itemPrice(item.getPrice())
				.itemDiscountRate(item.getDiscountRate())
				.count(dto.getCount())
				.cancelReason("")
				.detailReason("")
				.status(PurchaseListStatus.ACCEPTED)
				.wroteReview(false)
				.deleted(false)
				.build());

		// 주문 시 itemOption 재고 감소
		decreaseStockForPurchase(dto.getItemOptionId(), dto.getCount());
	}

	@Override
	@Transactional
	public void deletePurchaseList(PurchaseListDeleteReqDTO dto, UUID uuid) {
		Purchase purchase = purchaseRepository.findByPurchaseCodeAndUuid(dto.getPurchaseCode(), uuid)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.PURCHASE_NOT_FOUND));

		PurchaseList purchaseList = purchaseListRepository.findByIdAndPurchaseId(dto.getPurchaseListId(),
						purchase.getId())
				.orElseThrow(() -> new BaseException(BaseResponseStatus.PURCHASE_LIST_NOT_FOUND));

		Long purchaseId = purchaseList.getPurchaseId();
		Long itemOptionId = purchaseList.getItemOptionId();
		Integer count = purchaseList.getCount();

		purchaseListRepository.save(PurchaseList.builder()
				.id(purchaseList.getId())
				.itemOptionId(purchaseList.getItemOptionId())
				.count(purchaseList.getCount())
				.cancelReason(dto.getCancelReason())
				.detailReason(dto.getDetailReason())
				.wroteReview(purchaseList.getWroteReview())
				.deleted(false)
				.status(PurchaseListStatus.CANCEL)
				.build());

		// 주문 취소 시 재고 복구
		restoreStockForPurchase(itemOptionId, count);

		// 주문 상품이 하나도 없을 경우 주문 정보 삭제
		if (purchaseListRepository.existsPurchaseCanceled(purchaseId)) {
			purchaseRepository.updatePurchaseStatusToCancel(purchaseId);
		}
	}

	@Override
	public List<PurchasesGetRespDTO> getPurchases(UUID uuid, PurchaseGetReqDTO dto, Pageable page) {
		return purchaseRepository.findPurchaseByUuidAndDate(uuid, dto, page);
	}

	@Override
	@Transactional
	public void deletePurchase(String purchaseCode, UUID uuid) {
		Purchase purchase = purchaseRepository.findByPurchaseCodeAndUuid(purchaseCode, uuid)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.PURCHASE_NOT_FOUND));

		purchaseListRepository.deleteAllPurchaseList(purchase.getId());
		purchaseRepository.delete(purchase);
	}

	@Override
	public PurchaseListGetRespDTO getPurchaseList(Long purchaseListId, UUID uuid) {
		PurchaseListByIdAndUuidRespDTO dto = purchaseListRepository.getRespDTOByIdAndUuid(purchaseListId,
						uuid)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.PURCHASE_LIST_NOT_FOUND));

		Long itemId = itemOptionRepository.getItemIdById(dto.getItemOptionId())
				.orElseThrow(() -> new BaseException(BaseResponseStatus.ITEM_OPTION_NOT_FOUND));

		return PurchaseListGetRespDTO.builder()
				.itemId(itemId)
				.itemName(dto.getItemName())
				.createdDate(dto.getCreatedDate())
				.status(dto.getStatus())
				.build();
	}

	private void restoreStockForPurchase(Long itemOptionId, Integer count) {
		if (!itemOptionRepository.updateRestoreStock(itemOptionId, count)) {
			throw new BaseException(BaseResponseStatus.ITEM_STOCK_RESTORE_FAIL);
		}
	}

	private void decreaseStockForPurchase(Long itemOptionId, Integer count) {
		if (!itemOptionRepository.updateSubtractStock(itemOptionId, count)) {
			throw new BaseException(BaseResponseStatus.ITEM_STOCK_NOT_ENOUGH);
		}
	}

	private String makePurchaseCode() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String date = LocalDate.now().format(formatter);
		String randomString = generateRandomString();

		return date + randomString;
	}

	public String generateRandomString() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

		Random rand = new Random();

		return rand.ints(6, 0, characters.length())
				.mapToObj(characters::charAt)
				.map(Object::toString)
				.collect(Collectors.joining());
	}
}
