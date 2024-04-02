package com.comehere.ssgserver.purchase.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.common.security.jwt.JWTUtil;
import com.comehere.ssgserver.purchase.application.AddressService;
import com.comehere.ssgserver.purchase.infrastructure.AddressRepository;
import com.comehere.ssgserver.purchase.vo.resp.DefaultCheckRespVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/address")
@Tag(name = "Address", description = "배송지 컨트롤러")
public class AddressController {

	private final JWTUtil jwtUtil;
	private final AddressRepository addressRepository;
	private final AddressService addressService; // 'AddressService' has no 'getDefaultAddress' method
	private final ModelMapper modelMapper;

	@GetMapping("default/check")
	@Operation(summary = "기본 배송지 조회")
	public BaseResponse<?> userDefaultAddressCheck(@RequestHeader("Authorization") String accessToken) {

		return new BaseResponse<>(
				modelMapper.map(addressService.getDefaultAddress(jwtUtil.getUuidByAuthorization(accessToken)),
						DefaultCheckRespVO.class));
	}
}
