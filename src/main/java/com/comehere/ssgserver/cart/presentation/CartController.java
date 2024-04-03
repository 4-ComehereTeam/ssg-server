package com.comehere.ssgserver.cart.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.cart.application.CartService;
import com.comehere.ssgserver.cart.dto.request.AddProductReqDTO;
import com.comehere.ssgserver.cart.dto.request.ChangeCheckStateReqDTO;
import com.comehere.ssgserver.cart.dto.response.GetCartListRespDTO;
import com.comehere.ssgserver.cart.vo.request.AddProductReqVO;
import com.comehere.ssgserver.cart.vo.request.ChangeCheckStateReqVO;
import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.common.security.jwt.JWTUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cart")
@Tag(name = "Cart", description = "장바구니 컨트롤러")
public class CartController {

	private final CartService cartService;
	private final ModelMapper modelMapper;
	private final JWTUtil jwtUtil;

	@PostMapping("/product/add")
	@Operation(summary = "상품 장바구니에 추가")
	public BaseResponse<?> addProductToCart(@RequestHeader("Authorization") String accessToken,
			@RequestBody AddProductReqVO addProductReqVO) {

		AddProductReqDTO addProductReqDTO = modelMapper.map(addProductReqVO, AddProductReqDTO.class);

		return new BaseResponse<>(
				cartService.addProductToCart(jwtUtil.getUuidByAuthorization(accessToken), addProductReqDTO));
	}

	@GetMapping("/list")
	@Operation(summary = "장바구니 조회")
	public BaseResponse<?> getUserCartList(@RequestHeader("Authorization") String accessToken) {

		GetCartListRespDTO getCartListRespDTO = cartService.getCartList(jwtUtil.getUuidByAuthorization(accessToken));
		return new BaseResponse<>(modelMapper.map(getCartListRespDTO, GetCartListRespDTO.class));
	}

	@PutMapping("checkstate/change")
	@Operation(summary = "장바구니 상품 체크 상태 변경")
	public BaseResponse<?> changeProductCheckState(@RequestHeader("Authorization") String accessToken,
			@RequestBody ChangeCheckStateReqVO changeCheckStateReqVO) {
		ChangeCheckStateReqDTO changeCheckStateReqDTO = modelMapper.map(changeCheckStateReqVO,
				ChangeCheckStateReqDTO.class);
		return new BaseResponse<>(cartService.changeProductChangeState(jwtUtil.getUuidByAuthorization(accessToken),
				changeCheckStateReqDTO));
	}
}
