package com.comehere.ssgserver.purchase.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.purchase.domain.MemberPurchase;
import com.comehere.ssgserver.purchase.domain.PurchaseList;

public interface PurchaseListRepository extends JpaRepository<PurchaseList, Long> {
	List<PurchaseList> findByMemberPurchase(MemberPurchase memberPurchase);
}
