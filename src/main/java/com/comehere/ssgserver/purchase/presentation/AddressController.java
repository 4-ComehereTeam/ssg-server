package com.comehere.ssgserver.purchase.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.common.security.jwt.JWTUtil;
import com.comehere.ssgserver.purchase.application.AddressService;
import com.comehere.ssgserver.purchase.dto.req.AddressAddReqDTO;
import com.comehere.ssgserver.purchase.dto.req.AddressReqDTO;
import com.comehere.ssgserver.purchase.dto.req.ModifyAddressReqDTO;
import com.comehere.ssgserver.purchase.dto.req.ModifyRequestMessageReqDTO;
import com.comehere.ssgserver.purchase.vo.req.AddressAddReqVO;
import com.comehere.ssgserver.purchase.vo.req.AddressReqVO;
import com.comehere.ssgserver.purchase.vo.req.ModifyAddressReqVO;
import com.comehere.ssgserver.purchase.vo.req.ModifyRequestMessageReqVO;
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
	private final AddressService addressService;
	private final ModelMapper modelMapper;

	@GetMapping("/default")
	@Operation(summary = "기본 배송지 조회")
	public BaseResponse<DefaultCheckRespVO> userDefaultAddressCheck(
			@RequestHeader("Authorization") String accessToken) {

		return new BaseResponse<>(
				modelMapper.map(addressService.getDefaultAddress(jwtUtil.getUuidByAuthorization(accessToken)),
						DefaultCheckRespVO.class));
	}

	@PutMapping("/default/change")
	@Operation(summary = "기본 배송지 변경")
	public BaseResponse<Boolean> userDefaultAddressChange(@RequestHeader("Authorization") String accessToken,
			@RequestBody AddressReqVO addressReqVO) {

		return new BaseResponse<>(addressService.changeDefaultAddress(jwtUtil.getUuidByAuthorization(accessToken),
				modelMapper.map(addressReqVO, AddressReqDTO.class)));
	}

	@PostMapping("/")
	@Operation(summary = "배송지 추가")
	public BaseResponse<Boolean> addAddress(@RequestHeader("Authorization") String accessToken,
			@RequestBody AddressAddReqVO addressAddReqVO) {

		addressService.addAddress(jwtUtil.getUuidByAuthorization(accessToken),
				modelMapper.map(addressAddReqVO, AddressAddReqDTO.class));
		return new BaseResponse<>(true);
	}

	@GetMapping("/list")
	@Operation(summary = "배송지 조회")
	public BaseResponse<AddressListRespVO> userAddressDetail(@RequestHeader("Authorization") String accessToken) {

		return new BaseResponse<>(modelMapper.map(addressService
				.getAddressList(jwtUtil.getUuidByAuthorization(accessToken)), AddressListRespVO.class));
	}

	@DeleteMapping("/delete")
	@Operation(summary = "배송지 삭제")
	public BaseResponse<Boolean> deleteAddress(@RequestHeader("Authorization") String accessToken,
			@RequestBody AddressReqVO addressReqVO) {
		return new BaseResponse<>(addressService.deleteAddress(jwtUtil.getUuidByAuthorization(accessToken),
				modelMapper.map(addressReqVO, AddressReqDTO.class)));
	}

	@PutMapping("/modify")
	@Operation(summary = "배송지 정보 수정")
	public BaseResponse<Void> modifyUserAddress(@RequestHeader("Authorization") String accessToken,
			@RequestBody ModifyAddressReqVO modifyAddressReqVO) {
		addressService.updateAddressInfo(jwtUtil.getUuidByAuthorization(accessToken),
				modelMapper.map(modifyAddressReqVO, ModifyAddressReqDTO.class));
		return new BaseResponse<>();
	}

	@PutMapping("/request-message")
	@Operation(summary = "배송지 요청 메시지 수정")
	public BaseResponse<Boolean> modifyAddressRequestMessage(@RequestHeader("Authorization") String accessToken,
			@RequestBody ModifyRequestMessageReqVO modifyRequestMessageReqVO) {

		return new BaseResponse<>(
				addressService.updateAddressRequestMessage(jwtUtil.getUuidByAuthorization(accessToken),
						modelMapper.map(modifyRequestMessageReqVO, ModifyRequestMessageReqDTO.class)));
	}
}
