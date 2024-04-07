package com.comehere.ssgserver.cart.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.cart.application.CartService;
import com.comehere.ssgserver.cart.dto.req.AddItemReqDTO;
import com.comehere.ssgserver.cart.dto.req.ChangeStateReqDTO;
import com.comehere.ssgserver.cart.dto.req.DeleteItemReqDTO;
import com.comehere.ssgserver.cart.dto.req.ItemQuantityModifyReqDTO;
import com.comehere.ssgserver.cart.dto.resp.GetCartListRespDTO;
import com.comehere.ssgserver.cart.vo.req.AddItemReqVO;
import com.comehere.ssgserver.cart.vo.req.ChangeStateReqVO;
import com.comehere.ssgserver.cart.vo.req.DeleteItemReqVO;
import com.comehere.ssgserver.cart.vo.req.ItemQuantityModifyReqVO;
import com.comehere.ssgserver.cart.vo.resp.GetCartListRespVO;
import com.comehere.ssgserver.cart.vo.resp.ItemQuantityModifyRespVO;
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

	@PostMapping("/")
	@Operation(summary = "상품 장바구니에 추가")
	public BaseResponse<Boolean> addItemToCart(@RequestHeader("Authorization") String accessToken,
			@RequestBody AddItemReqVO addItemReqVO) {

		return new BaseResponse<>(
				cartService.addItemToCart(jwtUtil.getUuidByAuthorization(accessToken),
						modelMapper.map(addItemReqVO, AddItemReqDTO.class)));
	}

	@PutMapping("/minus")
	@Operation(summary = "장바구니 상품 수량 감소")
	public BaseResponse<ItemQuantityModifyRespVO> minusItemQuantity(@RequestHeader("Authorization") String accessToken,
			@RequestBody ItemQuantityModifyReqVO itemQuantityModifyReqVO) {

		return new BaseResponse<>(
				modelMapper.map(cartService.minusItemQuantity(jwtUtil.getUuidByAuthorization(accessToken),
								modelMapper.map(itemQuantityModifyReqVO, ItemQuantityModifyReqDTO.class)),
						ItemQuantityModifyRespVO.class));
	}

	@PutMapping("/plus")
	@Operation(summary = "장바구니 상품 수량 증가")
	public BaseResponse<ItemQuantityModifyRespVO> plustItemQuantity(@RequestHeader("Authorization") String accessToken,
			@RequestBody ItemQuantityModifyReqVO itemQuantityModifyReqVO) {

		return new BaseResponse<>(
				modelMapper.map(cartService.plusItemQuantity(jwtUtil.getUuidByAuthorization(accessToken),
								modelMapper.map(itemQuantityModifyReqVO, ItemQuantityModifyReqDTO.class)),
						ItemQuantityModifyRespVO.class));
	}

	@DeleteMapping("/delete")
	@Operation(summary = "장바구니 상품 삭제")

	public BaseResponse<Boolean> deleteItemFromCart(@RequestHeader("Authorization") String accessToken,
			@RequestBody DeleteItemReqVO deleteItemReqVO) {
		return new BaseResponse<>(cartService.deleteItemFromCart(jwtUtil.getUuidByAuthorization(accessToken),
				modelMapper.map(deleteItemReqVO, DeleteItemReqDTO.class)));
	}

	@GetMapping("/list")
	@Operation(summary = "장바구니 조회")
	public BaseResponse<GetCartListRespVO> getUserCartList(@RequestHeader("Authorization") String accessToken) {

		GetCartListRespDTO getCartListRespDTO = cartService.getCartList(jwtUtil.getUuidByAuthorization(accessToken));
		return new BaseResponse<>(modelMapper.map(getCartListRespDTO, GetCartListRespVO.class));
	}

	@PutMapping("/check-state/change")
	@Operation(summary = "장바구니 상품 체크 상태 변경")
	public BaseResponse<Boolean> changeProductCheckState(@RequestHeader("Authorization") String accessToken,
			@RequestBody ChangeStateReqVO changeStateReqVO) {
		return new BaseResponse<>(cartService.changeItemChangeState(jwtUtil.getUuidByAuthorization(accessToken),
				modelMapper.map(changeStateReqVO, ChangeStateReqDTO.class)));
	}

	@PutMapping("/pin-state/change")
	@Operation(summary = "장바구니 상품 핀 상태 변경")
	public BaseResponse<Boolean> changeProductPinState(@RequestHeader("Authorization") String accessToken,
			@RequestBody ChangeStateReqVO changeStateReqVO) {

		return new BaseResponse<>(cartService.changeItemPinState(jwtUtil.getUuidByAuthorization(accessToken),
				modelMapper.map(changeStateReqVO, ChangeStateReqDTO.class)));
	}
}
