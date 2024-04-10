package com.comehere.ssgserver.purchase.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyRequestMessageReqDTO {

	private Long addressId;
	private String newMessage;
}
