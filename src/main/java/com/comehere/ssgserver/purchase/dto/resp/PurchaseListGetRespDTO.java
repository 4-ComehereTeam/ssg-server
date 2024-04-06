package com.comehere.ssgserver.purchase.dto.resp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.comehere.ssgserver.purchase.domain.PurchaseListStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PurchaseListGetRespDTO {
	private Long itemId;

	private String itemName;

	private String createdDate;

	private String status;

	@Builder
	public PurchaseListGetRespDTO(Long itemId, String itemName, String createdDate, String status) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.createdDate = createdDate;
		this.status = status;
	}
}
