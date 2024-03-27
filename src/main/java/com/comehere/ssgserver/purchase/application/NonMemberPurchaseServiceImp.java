package com.comehere.ssgserver.purchase.application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.option.domain.ItemOption;
import com.comehere.ssgserver.option.infrastructure.ItemOptionRepository;
import com.comehere.ssgserver.purchase.domain.NonMemberPurchase;
import com.comehere.ssgserver.purchase.dto.NonMemberPurchaseGetDTO;
import com.comehere.ssgserver.purchase.infrastructure.NonMemberPurchaseRepository;
import com.comehere.ssgserver.purchase.vo.NonMemberPurchaseCreateVO;
import com.comehere.ssgserver.purchase.vo.NonMemberPurchaseDeleteVO;
import com.comehere.ssgserver.purchase.vo.NonMemberPurchaseGetVO;
import com.comehere.ssgserver.purchase.vo.NonMemberPurchaseOptionItemUpdateVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NonMemberPurchaseServiceImp implements NonMemberPurchaseService {
	private final NonMemberPurchaseRepository nonMemberPurchaseRepository;

	private final ItemOptionRepository itemOptionRepository;

	@Override
	public void createNonMemberPurchase(NonMemberPurchaseCreateVO nonMemberPurchaseCreateVO) {
		NonMemberPurchase nonMemberPurchase = NonMemberPurchase.builder()
				.itemOptionId(nonMemberPurchaseCreateVO.getItemOptionId())
				.itemName(nonMemberPurchaseCreateVO.getItemName())
				.itemPrice(nonMemberPurchaseCreateVO.getItemPrice())
				.count(nonMemberPurchaseCreateVO.getCount())
				.nonMemberName(nonMemberPurchaseCreateVO.getNonMemberName())
				.phoneNumber(nonMemberPurchaseCreateVO.getPhoneNumber())
				.address(nonMemberPurchaseCreateVO.getAddress())
				.addressDetail(nonMemberPurchaseCreateVO.getAddressDetail())
				.zipcode(nonMemberPurchaseCreateVO.getZipcode())
				.requestMessage(nonMemberPurchaseCreateVO.getRequestMessage())
				.email(nonMemberPurchaseCreateVO.getEmail())
				.purchaseCode(makePurchaseCode())
				.deleted(false)
				.cancellationReasons("")
				.build();

		nonMemberPurchaseRepository.save(nonMemberPurchase);
	}

	@Override
	public NonMemberPurchaseGetDTO getPurchase(NonMemberPurchaseGetVO nonMemberPurchaseGetVO) {
		NonMemberPurchase nonMemberPurchase = nonMemberPurchaseRepository.findByNonMemberNameAndPhoneNumberAndPurchaseCode(
						nonMemberPurchaseGetVO.getNonMemberName(),
						nonMemberPurchaseGetVO.getPhoneNumber(), nonMemberPurchaseGetVO.getPurchaseCode())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));

		ItemOption itemOption = itemOptionRepository.findById(nonMemberPurchase.getItemOptionId())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
		Long itemId = itemOption.getItem().getId();

		return NonMemberPurchaseGetDTO.builder()
				.itemId(itemId)
				.itemOptionId(nonMemberPurchase.getItemOptionId())
				.itemName(nonMemberPurchase.getItemName())
				.itemPrice(nonMemberPurchase.getItemPrice())
				.count(nonMemberPurchase.getCount())
				.build();
	}

	@Override
	@Transactional
	public void deleteNonMemberPurchase(NonMemberPurchaseDeleteVO vo) {
		NonMemberPurchase nonMemberPurchase = nonMemberPurchaseRepository.findById(
						vo.getNonMemberPurchaseId())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));

		NonMemberPurchase updateNonMemberPurchase = NonMemberPurchase.builder()
				.id(nonMemberPurchase.getId())
				.itemOptionId(nonMemberPurchase.getItemOptionId())
				.cancellationReasons(vo.getCancellationReasons())
				.detailReasons(vo.getDetailReasons())
				.deleted(true)
				.build();

		nonMemberPurchaseRepository.save(updateNonMemberPurchase);

		// nonMemberPurchaseRepository.delete(updateNonMemberPurchase);
	}

	@Override
	public void updateNonMemberPurchaseOptionItem(
			NonMemberPurchaseOptionItemUpdateVO vo) {

		NonMemberPurchase nonMemberPurchase = nonMemberPurchaseRepository.findById(
						vo.getNonMemberPurchaseId())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));

		NonMemberPurchase updateNonMemberPurchase = NonMemberPurchase.builder()
				.id(nonMemberPurchase.getId())
				.itemOptionId(vo.getItemOptionId())
				.cancellationReasons(nonMemberPurchase.getCancellationReasons())
				.detailReasons(nonMemberPurchase.getDetailReasons())
				.deleted(nonMemberPurchase.getDeleted())
				.build();

		nonMemberPurchaseRepository.save(updateNonMemberPurchase);
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
