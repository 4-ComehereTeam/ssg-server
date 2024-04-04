package com.comehere.ssgserver.option.dto.resp;

import com.comehere.ssgserver.option.domain.ItemOption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class OptionDTO {
	private Boolean color;
	private Boolean size;
	private Boolean etc;

	public OptionDTO(ItemOption io) {
		if(io.getColor() != null) {
			this.color = true;
		}

		if(io.getSize() != null) {
			this.size = true;
		}

		if(io.getEtc() != null) {
			this.etc = true;
		}
	}
}
