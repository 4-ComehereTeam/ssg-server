package com.comehere.ssgserver.purchase.application;

import com.comehere.ssgserver.purchase.dto.PurchaseIdDTO;
import com.comehere.ssgserver.purchase.vo.MemberPurchaseDeliveriesItemsCreateVO;
import com.comehere.ssgserver.purchase.vo.MemberPurchaseIdVO;
import com.comehere.ssgserver.purchase.vo.MemberPurchaseItemsCreateVO;
import com.comehere.ssgserver.purchase.vo.MemberPurchaseQuickCreateVO;

public interface MemberPurchaseService {

	void createMemberQuickPurchase(MemberPurchaseQuickCreateVO memberPurchaseQuickCreateVO);

	void createMemberPurchaseItems(MemberPurchaseItemsCreateVO memberPurchaseItemsCreateVO);

	void createMemberPurchaseItemsWithDeliveries(
			MemberPurchaseDeliveriesItemsCreateVO memberPurchaseDeliveriesItemsCreateVO);

	PurchaseIdDTO getMemberPurchases(Long memberId);

	void deleteMemberPurchase(MemberPurchaseIdVO memberPurchaseIdVO);

	void deletePurchaseList(Long purchaseListId);
}
