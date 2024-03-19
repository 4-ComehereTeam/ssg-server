package com.comehere.ssgserver.option.dto;

import com.comehere.ssgserver.option.domain.ItemOption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionDTO {
	private Long stockId;
	private String color;
	private String size;
	private String etc;
	private Integer stock;

	public OptionDTO(ItemOption io) {
		this.stockId = io.getId();

		if(io.getColor() != null) {
			this.color = io.getColor().getValue();
		}

		if(io.getSize() != null) {
			this.size = io.getSize().getValue();
		}

		if(io.getEtc() != null) {
			this.etc = io.getEtc().getValue();
		}

		this.stock = io.getStock();
	}
}
