package com.comehere.ssgserver.purchase.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.purchase.domain.MemberPurchase;

public interface MemberPurchaseRepository extends JpaRepository<MemberPurchase, Long> {
	List<MemberPurchase> findByMemberId(Long memberId);
}
