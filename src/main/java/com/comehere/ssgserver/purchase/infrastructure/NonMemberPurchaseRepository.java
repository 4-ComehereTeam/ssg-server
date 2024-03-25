package com.comehere.ssgserver.purchase.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.purchase.domain.NonMemberPurchase;

public interface NonMemberPurchaseRepository extends JpaRepository<NonMemberPurchase, Long> {
	Optional<NonMemberPurchase> findByNonMemberNameAndPhoneNumberAndCode(String nonMemberName, String phoneNumber,
			String code);
}
