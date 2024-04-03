package com.comehere.ssgserver.purchase.dto.resp;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressListRespDTO {

	List<Long> addressIds;
}
