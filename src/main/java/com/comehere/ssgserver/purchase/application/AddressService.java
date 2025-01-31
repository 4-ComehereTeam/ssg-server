package com.comehere.ssgserver.purchase.application;

import java.util.UUID;

import com.comehere.ssgserver.purchase.dto.req.AddressAddReqDTO;
import com.comehere.ssgserver.purchase.dto.req.AddressReqDTO;
import com.comehere.ssgserver.purchase.dto.req.ModifyAddressReqDTO;
import com.comehere.ssgserver.purchase.dto.req.ModifyRequestMessageReqDTO;
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
	Boolean changeDefaultAddress(UUID uuid, AddressReqDTO addressReqDTO);

	//배송지 수정
	void updateAddressInfo(UUID uuid, ModifyAddressReqDTO modifyAddressReqDTO);

	Boolean updateAddressRequestMessage(UUID uuid, ModifyRequestMessageReqDTO modifyRequestMessageReqDTO);

	//배송지 삭제
	Boolean deleteAddress(UUID uuid, AddressReqDTO addressReqDTO);
}
