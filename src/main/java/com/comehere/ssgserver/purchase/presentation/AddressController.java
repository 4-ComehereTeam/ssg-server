package com.comehere.ssgserver.purchase.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.common.security.jwt.JWTUtil;
import com.comehere.ssgserver.purchase.application.AddressService;
import com.comehere.ssgserver.purchase.dto.req.AddressAddReqDTO;
import com.comehere.ssgserver.purchase.dto.req.AddressDetailReqDTO;
import com.comehere.ssgserver.purchase.vo.req.AddressAddReqVO;
import com.comehere.ssgserver.purchase.vo.req.AddressDatailReqVO;
import com.comehere.ssgserver.purchase.vo.resp.AddressDetailRespVO;
import com.comehere.ssgserver.purchase.vo.resp.AddressListRespVO;
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
	private final AddressService addressService; // 'AddressService' has no 'getDefaultAddress' method
	private final ModelMapper modelMapper;

	@GetMapping("/default/check")
	@Operation(summary = "기본 배송지 조회")
	public BaseResponse<?> userDefaultAddressCheck(@RequestHeader("Authorization") String accessToken) {

		return new BaseResponse<>(
				modelMapper.map(addressService.getDefaultAddress(jwtUtil.getUuidByAuthorization(accessToken)),
						DefaultCheckRespVO.class));
	}

	@PostMapping("/add")
	@Operation(summary = "배송지 추가")
	public BaseResponse<?> userAddressAdd(@RequestHeader("Authorization") String accessToken,
			@RequestBody AddressAddReqVO addressAddReqVO) {

		AddressAddReqDTO addressAddReqDTO = modelMapper.map(addressAddReqVO, AddressAddReqDTO.class);
		addressService.addAddress(jwtUtil.getUuidByAuthorization(accessToken), addressAddReqDTO);
		return new BaseResponse<>(true);
	}

	@GetMapping("/list")
	@Operation(summary = "회원 배송지 목록 조회")
	public BaseResponse<?> userAddressList(@RequestHeader("Authorization") String accessToken) {

		AddressListRespVO addressListRespVO = modelMapper
				.map(addressService.getAddressList(jwtUtil.getUuidByAuthorization(accessToken)),
						AddressListRespVO.class);
		return new BaseResponse<>(addressListRespVO);
	}

	@PostMapping("/detail")
	@Operation(summary = "배송지 상세 조회")
	public BaseResponse<?> userAddressDetail(@RequestBody AddressDatailReqVO addressDatailReqVO) {

		AddressDetailReqDTO addressDetailReqDTO = modelMapper.map(addressDatailReqVO, AddressDetailReqDTO.class);
		
		return new BaseResponse<>(modelMapper
				.map(addressService.getAddressDetail(addressDetailReqDTO), AddressDetailRespVO.class));
	}
}
