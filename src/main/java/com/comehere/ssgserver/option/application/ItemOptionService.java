package com.comehere.ssgserver.option.application;

import com.comehere.ssgserver.option.dto.resp.ColorRespDTO;
import com.comehere.ssgserver.option.dto.resp.EtcRespDTO;
import com.comehere.ssgserver.option.dto.resp.ItemOptionIdRespDTO;
import com.comehere.ssgserver.option.dto.resp.ItemOptionInfoRespDTO;
import com.comehere.ssgserver.option.dto.resp.ItemOptionRespDTO;
import com.comehere.ssgserver.option.dto.resp.HasOptionRespDTO;
import com.comehere.ssgserver.option.dto.resp.SizeRespDTO;

public interface ItemOptionService {

	HasOptionRespDTO hasOptions(Long itemId);

	ColorRespDTO getColors(Long itemId);

	SizeRespDTO getSizes(Long itemId, Long colorId);

	EtcRespDTO getEtcs(Long itemId, Long colorId, Long sizeId);

	ItemOptionIdRespDTO getOptionId(Long reviewId);

	ItemOptionInfoRespDTO getOptionInfo(Long itemId);
}
