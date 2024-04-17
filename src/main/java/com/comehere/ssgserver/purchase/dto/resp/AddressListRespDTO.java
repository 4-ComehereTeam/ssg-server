package com.comehere.ssgserver.purchase.dto.resp;

import java.util.List;

import com.comehere.ssgserver.purchase.dto.AddressDetailDTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressListRespDTO {

	List<AddressDetailDTO> addressIds;
}
