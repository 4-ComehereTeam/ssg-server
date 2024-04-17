package com.comehere.ssgserver.purchase.dto.resp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.comehere.ssgserver.purchase.domain.PurchaseListStatus;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PurchaseListByIdAndUuidRespDTO {
	private Long itemOptionId;

	private String itemName;

	private String createdDate;

	private String status;

	@Builder
	@QueryProjection
	public PurchaseListByIdAndUuidRespDTO(Long itemOptionId, String itemName, LocalDateTime createdDate,
			PurchaseListStatus status) {
		this.itemOptionId = itemOptionId;
		this.itemName = itemName;
		this.createdDate = formatLocalDateTimeToDate(createdDate);
		this.status = status.getDescription();
	}

	private String formatLocalDateTimeToDate(LocalDateTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

		return time.format(formatter);
	}
}
