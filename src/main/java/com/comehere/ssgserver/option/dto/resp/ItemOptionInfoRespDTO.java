package com.comehere.ssgserver.option.dto.resp;

import java.util.List;

import com.comehere.ssgserver.option.vo.resp.OptionInfoVO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemOptionInfoRespDTO {
	private Long OptionId;

	private List<OptionInfoVO> options;
}
