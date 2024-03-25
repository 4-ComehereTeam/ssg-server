package com.comehere.ssgserver.purchase.application;

import com.comehere.ssgserver.purchase.dto.NonMemberPurchaseGetDTO;
import com.comehere.ssgserver.purchase.vo.NonMemberPurchaseCreateVO;
import com.comehere.ssgserver.purchase.vo.NonMemberPurchaseDeleteVO;
import com.comehere.ssgserver.purchase.vo.NonMemberPurchaseGetVO;
import com.comehere.ssgserver.purchase.vo.NonMemberPurchaseOptionItemUpdateVO;

public interface NonMemberPurchaseService {
	void createNonMemberPurchase(NonMemberPurchaseCreateVO nonMemberPurchaseCreateVO);

	NonMemberPurchaseGetDTO getPurchase(NonMemberPurchaseGetVO nonMemberPurchaseGetVO);

	void deleteNonMemberPurchase(NonMemberPurchaseDeleteVO nonMemberPurchaseDeleteVO);

	void updateNonMemberPurchaseOptionItem(NonMemberPurchaseOptionItemUpdateVO nonMemberPurchaseOptionItemUpdateVO);
}
