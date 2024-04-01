package com.comehere.ssgserver.purchase.vo.resp;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PurchasesGetRespVO {
	private String purchaseCode;

	private List<Long> purchaseListIds;
}
