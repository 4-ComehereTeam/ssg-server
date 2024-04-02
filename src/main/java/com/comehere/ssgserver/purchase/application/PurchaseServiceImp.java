package com.comehere.ssgserver.purchase.application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.common.exception.BaseException;
import com.comehere.ssgserver.common.response.BaseResponseStatus;
import com.comehere.ssgserver.purchase.domain.Purchase;
import com.comehere.ssgserver.purchase.domain.PurchaseList;
import com.comehere.ssgserver.purchase.dto.req.PurchaseCreateReqDTO;
import com.comehere.ssgserver.purchase.dto.req.PurchaseListCreateReqDTO;
import com.comehere.ssgserver.purchase.dto.req.PurchaseListDeleteReqDTO;
import com.comehere.ssgserver.purchase.dto.resp.PurchasesGetRespDTO;
import com.comehere.ssgserver.purchase.infrastructure.PurchaseListRepository;
import com.comehere.ssgserver.purchase.infrastructure.PurchaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImp implements PurchaseService {
	private final PurchaseRepository purchaseRepository;

	private final PurchaseListRepository purchaseListRepository;

	@Override
	@Transactional
	public void createPurchase(PurchaseCreateReqDTO dto, UUID uuid) {
		Purchase purchase = Purchase.builder()
				.purchaseCode(makePurchaseCode())
				.uuid(uuid)
				.name(dto.getName())
				.email(dto.getEmail())
				.phone(dto.getPhone())
				.addressNickname(dto.getAddressNickname())
				.address(dto.getAddress())
				.detailAddress(dto.getDetailAddress())
				.zipcode(dto.getZipcode())
				.requestMessage(dto.getRequestMessage())
				.deleted(false)
				.build();

		purchaseRepository.save(purchase);

		dto.getPurchases()
				.forEach(purchaseListCreateReqDTO -> createPurchaseList(purchaseListCreateReqDTO, purchase.getId()));
	}

	@Override
	@Transactional
	public void createPurchaseList(PurchaseListCreateReqDTO dto, Long purchaseId) {
		if (purchaseListRepository.existsByPurchaseIdAndItemOptionId(purchaseId, dto.getItemOptionId())) {
			throw new BaseException(BaseResponseStatus.PURCHASE_LIST_DUPLICATE);
		}

		purchaseListRepository.save(PurchaseList.builder()
				.purchaseId(purchaseId)
				.itemOptionId(dto.getItemOptionId())
				.itemName(dto.getItemName())
				.itemPrice(dto.getItemPrice())
				.itemDiscountRate(dto.getItemDiscountRate())
				.count(dto.getCount())
				.cancelReason("")
				.detailReason("")
				.wroteReview(false)
				.deleted(false)
				.build());
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

		purchaseListRepository.save(PurchaseList.builder()
				.id(purchaseList.getId())
				.count(purchaseList.getCount())
				.cancelReason(dto.getCancelReason())
				.detailReason(dto.getDetailReason())
				.wroteReview(purchaseList.getWroteReview())
				.deleted(true)
				.build());

		// 주문 상품이 하나도 없을 경우 주문 정보 삭제
		if (!purchaseListRepository.existsByPurchaseId(purchaseId)) {
			purchaseRepository.delete(purchase);
		}
	}

	@Override
	public List<PurchasesGetRespDTO> getPurchases(UUID uuid) {
		return purchaseRepository.findByUuid(uuid)
				.stream()
				.map(purchase -> PurchasesGetRespDTO.builder()
						.purchaseCode(purchase.getPurchaseCode())
						.purchaseListIds(purchaseListRepository.findByPurchaseId(purchase.getId())
								.stream()
								.map(PurchaseList::getId)
								.collect(Collectors.toList()))
						.build())
				.collect(Collectors.toList());
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
