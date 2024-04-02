package com.comehere.ssgserver.purchase.application;

import java.util.UUID;

import com.comehere.ssgserver.purchase.dto.resp.DefaultCheckRespDTO;

public interface AddressService {
	DefaultCheckRespDTO getDefaultAddress(UUID uuid);
}
