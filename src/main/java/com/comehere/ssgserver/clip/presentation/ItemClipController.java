package com.comehere.ssgserver.clip.presentation;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.clip.application.ItemClipService;
import com.comehere.ssgserver.clip.dto.req.ItemsClipDeleteReqDTO;
import com.comehere.ssgserver.clip.vo.req.ItemsClipDeleteReqVO;
import com.comehere.ssgserver.clip.vo.resp.ItemClipGetRespVO;
import com.comehere.ssgserver.clip.vo.resp.ItemsClipGetRespVO;
import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.common.security.jwt.JWTUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/clip")
@RequiredArgsConstructor
@Tag(name = "item clip", description = "상품 좋아요 컨트롤러")
public class ItemClipController {
	private final ItemClipService itemClipService;

	private final JWTUtil jwtUtil;

	private final ModelMapper modelMapper;

	@Operation(summary = "상품 좋아요")
	@PostMapping("/item/{itemId}")
	public BaseResponse<?> createItemClip(
			@PathVariable("itemId") Long itemId,
			@RequestHeader("Authorization") String accessToken) {

		UUID uuid = jwtUtil.getUuidByAuthorization(accessToken);
		itemClipService.createItemClip(uuid, itemId);

		return new BaseResponse<>();
	}

	@Operation(summary = "상품 좋아요 취소")
	@DeleteMapping("/item/{itemId}")
	public BaseResponse<?> deleteItemClip(
			@PathVariable("itemId") Long itemId,
			@RequestHeader("Authorization") String accessToken) {

		UUID uuid = jwtUtil.getUuidByAuthorization(accessToken);
		itemClipService.deleteItemClip(uuid, itemId);

		return new BaseResponse<>();
	}

	@Operation(summary = "상품 좋아요 여러개 취소")
	@DeleteMapping("/items")
	public BaseResponse<?> deleteItemsClip(
			@RequestBody ItemsClipDeleteReqVO vo,
			@RequestHeader("Authorization") String accessToken) {

		UUID uuid = jwtUtil.getUuidByAuthorization(accessToken);
		itemClipService.deleteItemsClip(uuid, modelMapper.map(vo, ItemsClipDeleteReqDTO.class));

		return new BaseResponse<>();
	}

	@Operation(summary = "상품 좋아요 단건 조회")
	@GetMapping("/item/{itemId}")
	public BaseResponse<ItemClipGetRespVO> getItemClip(
			@RequestHeader("Authorization") String accessToken,
			@PathVariable("itemId") Long itemId) {

		UUID uuid = jwtUtil.getUuidByAuthorization(accessToken);

		return new BaseResponse<>(modelMapper.map(itemClipService.getItemClip(itemId, uuid), ItemClipGetRespVO.class));
	}

	@Operation(summary = "상품 좋아요 목록 조회")
	@GetMapping("/items")
	public BaseResponse<ItemsClipGetRespVO> getItemsClip(@RequestHeader("Authorization") String accessToken) {

		UUID uuid = jwtUtil.getUuidByAuthorization(accessToken);

		return new BaseResponse<>(modelMapper.map(itemClipService.getItemsClip(uuid), ItemsClipGetRespVO.class));
	}
}

