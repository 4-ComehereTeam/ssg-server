package com.comehere.ssgserver.purchase.application;

import java.util.UUID;

import com.comehere.ssgserver.purchase.dto.req.AddressAddReqDTO;
import com.comehere.ssgserver.purchase.dto.req.DefaultChangeReqDTO;
import com.comehere.ssgserver.purchase.dto.resp.AddressListRespDTO;
import com.comehere.ssgserver.purchase.dto.resp.DefaultCheckRespDTO;

public interface AddressService {

	//배송지 추가
	void addAddress(UUID uuid, AddressAddReqDTO addressAddReqDTO);

	//기본 배송지 조회
	DefaultCheckRespDTO getDefaultAddress(UUID uuid);

	//배송지 목록 조회
	AddressListRespDTO getAddressList(UUID uuid);

	//기본 배송지 변경
	Boolean changeDefaultAddress(UUID uuid, DefaultChangeReqDTO defaultChangeReqDTO);
}
