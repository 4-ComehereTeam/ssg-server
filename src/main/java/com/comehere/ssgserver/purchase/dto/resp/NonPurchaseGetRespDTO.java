package com.comehere.ssgserver.purchase.dto.resp;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NonPurchaseGetRespDTO {
	private List<Long> purchaseListIds;
}
