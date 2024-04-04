package com.comehere.ssgserver.purchase.application;

import java.util.UUID;

import com.comehere.ssgserver.purchase.dto.req.AddressAddReqDTO;
import com.comehere.ssgserver.purchase.dto.resp.AddressListRespDTO;
import com.comehere.ssgserver.purchase.dto.resp.DefaultCheckRespDTO;

public interface AddressService {
	DefaultCheckRespDTO getDefaultAddress(UUID uuid);

	void addAddress(UUID uuid, AddressAddReqDTO addressAddReqDTO);

	AddressListRespDTO getAddressList(UUID uuid);

}
