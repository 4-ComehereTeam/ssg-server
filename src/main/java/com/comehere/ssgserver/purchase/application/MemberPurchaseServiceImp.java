package com.comehere.ssgserver.purchase.application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.purchase.domain.MemberPurchase;
import com.comehere.ssgserver.purchase.domain.PurchaseList;
import com.comehere.ssgserver.purchase.dto.PurchaseIdDTO;
import com.comehere.ssgserver.purchase.infrastructure.AddressRepository;
import com.comehere.ssgserver.purchase.infrastructure.MemberPurchaseRepository;
import com.comehere.ssgserver.purchase.infrastructure.PurchaseListRepository;
import com.comehere.ssgserver.purchase.vo.MemberPurchaseDeliveriesItemsCreateVO;
import com.comehere.ssgserver.purchase.vo.MemberPurchaseIdVO;
import com.comehere.ssgserver.purchase.vo.MemberPurchaseItemsCreateVO;
import com.comehere.ssgserver.purchase.vo.MemberPurchaseQuickCreateVO;
import com.comehere.ssgserver.purchase.vo.MemberPurchaseVO;
import com.comehere.ssgserver.purchase.vo.PurchaseItemOptionVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberPurchaseServiceImp implements MemberPurchaseService {
	private final MemberPurchaseRepository memberPurchaseRepository;

	private final PurchaseListRepository purchaseListRepository;

	private final AddressRepository addressRepository;

	@Override
	@Transactional
	public void createMemberQuickPurchase(MemberPurchaseQuickCreateVO memberPurchaseQuickCreateVO) {
		MemberPurchase memberPurchase = getMemberPurchase(memberPurchaseQuickCreateVO.getMemberPurchaseVO());
		memberPurchaseRepository.save(memberPurchase);

		PurchaseList purchaseList = getPurchaseList(memberPurchaseQuickCreateVO.getPurchaseItemOptionVO(),
				memberPurchase);

		purchaseListRepository.save(purchaseList);

	}

	@Override
	@Transactional
	public void createMemberPurchaseItems(MemberPurchaseItemsCreateVO vo) {
		MemberPurchase memberPurchase = getMemberPurchase(vo.getMemberPurchaseVO());
		memberPurchaseRepository.save(memberPurchase);

		vo.getItemOptions().stream()
				.map(purchaseItemOptionVO -> getPurchaseList(purchaseItemOptionVO, memberPurchase))
				.forEach(purchaseListRepository::save);
	}

	@Override
	public void createMemberPurchaseItemsWithDeliveries(
			MemberPurchaseDeliveriesItemsCreateVO memberPurchaseDeliveriesItemsCreateVO) {

		// use stream()
		memberPurchaseDeliveriesItemsCreateVO.getMemberPurchaseVOS()
				.stream()
				.map(this::getMemberPurchase)
				.forEach(memberPurchaseRepository::save);
	}

	@Override
	public PurchaseIdDTO getMemberPurchases(Long memberId) {
		List<MemberPurchase> memberPurchases = memberPurchaseRepository.findByMemberId(memberId);

		List<Long> tmp = new ArrayList<>();

		// use stream()
		memberPurchases.stream()
				.map(MemberPurchase::getId)
				.forEach(tmp::add);

		return PurchaseIdDTO.builder()
				.purchaseIds(tmp)
				.build();
	}

	@Override
	public void deleteMemberPurchase(MemberPurchaseIdVO memberPurchaseIdVO) {
		Long memberPurchaseId = memberPurchaseIdVO.getMemberPurchaseId();

		MemberPurchase memberPurchase = memberPurchaseRepository.findById(memberPurchaseId)
				.orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));

		deleteAllPurchaseByMemberPurchase(memberPurchase);
	}

	@Override
	public void deletePurchaseList(Long purchaseListId) {
		PurchaseList purchaseList = purchaseListRepository.findById(purchaseListId)
				.orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));

		purchaseListRepository.delete(purchaseList);
	}

	private void deleteAllPurchaseByMemberPurchase(MemberPurchase memberPurchase) {
		List<PurchaseList> purchaseLists = purchaseListRepository.findByMemberPurchase(memberPurchase);
		purchaseListRepository.deleteAll(purchaseLists);
	}

	private MemberPurchase getMemberPurchase(MemberPurchaseVO vo) {
		addressRepository.findById(vo.getMemberAddressId())
				.orElseThrow(() -> new IllegalArgumentException("주소를 찾을 수 없습니다."));

		MemberPurchase memberPurchase = MemberPurchase.builder()
				.purchaseCode(makePurchaseCode())
				.purchaseDate(LocalDateTime.now())
				.memberAddressId(vo.getMemberAddressId())
				.requestMessage(vo.getRequestMessage())
				.memberId(vo.getMemberId())
				.build();

		updateAddressRequestMessage(vo);

		return memberPurchase;
	}

	// addressService 로 이동 필요
	private void updateAddressRequestMessage(MemberPurchaseVO vo) {
		if (vo.getRequestMessageSave()) {
			addressRepository.findById(vo.getMemberAddressId())
					.ifPresent(
							address -> address.updateRequestMessage(vo.getRequestMessage()));
		}
	}

	private PurchaseList getPurchaseList(PurchaseItemOptionVO purchaseItemOptionVO,
			MemberPurchase memberPurchase) {

		return PurchaseList.builder()
				.memberPurchase(memberPurchase)
				.itemOptionId(purchaseItemOptionVO.getItemOptionId())
				.itemName(purchaseItemOptionVO.getItemName())
				.itemPrice(purchaseItemOptionVO.getItemPrice())
				.count(purchaseItemOptionVO.getCount())
				.cancellationReasons("")
				.detailReasons("")
				.reviewWrite(false)
				.deleted(false)
				.build();
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
