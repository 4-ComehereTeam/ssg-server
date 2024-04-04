package com.comehere.ssgserver.purchase.vo.resp;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NonPurchaseGetRespVO {
	private String purchaseCode;

	private List<Long> purchaseListIds;
}
