package com.comehere.ssgserver.option.vo.resp;

import java.util.List;

import com.comehere.ssgserver.option.dto.resp.OptionInfoDTO;

import lombok.Getter;

@Getter
public class ItemOptionInfoRespVO {
	private Long OptionId;

	private List<OptionInfoDTO> options;
}
