package com.comehere.ssgserver.item.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.common.security.jwt.JWTUtil;
import com.comehere.ssgserver.item.application.ItemService;
import com.comehere.ssgserver.item.dto.req.DeleteRecentViewReqDTO;
import com.comehere.ssgserver.item.dto.req.ItemListReqDTO;
import com.comehere.ssgserver.item.dto.req.ItemReqDTO;
import com.comehere.ssgserver.item.vo.req.DeleteRecentViewReqVO;
import com.comehere.ssgserver.item.vo.req.ItemReqVO;
import com.comehere.ssgserver.item.vo.resp.ItemCalcRespVO;
import com.comehere.ssgserver.item.vo.resp.ItemDetailRespVO;
import com.comehere.ssgserver.item.vo.resp.ItemImageListRespVO;
import com.comehere.ssgserver.item.vo.resp.ItemListRespVO;
import com.comehere.ssgserver.item.vo.resp.ItemThumbnailRespVO;
import com.comehere.ssgserver.item.vo.resp.RecentViewListRespVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
@Tag(name = "items", description = "상품 관리 컨트롤러")
@Slf4j
public class ItemController {
	private final ItemService itemService;
	private final ModelMapper modelMapper;
	private final JWTUtil jwtUtil;

	@GetMapping("/detail/{itemId}")
	@Operation(summary = "상품 기본 정보 API", description = "상품 기본 정보 (상품명, 가격) 조회")
	public BaseResponse<ItemDetailRespVO> itemDetail(@PathVariable("itemId") Long id) {
		log.info("요청 받음");
		return new BaseResponse<>(modelMapper.map(itemService.getItemDetail(id), ItemDetailRespVO.class));
	}

	@GetMapping("/description/{itemId}")
	@Operation(summary = "상품 상세 정보 API", description = "상품 상세 정보 조회")
	public BaseResponse<String> getItemDescription(@PathVariable("itemId") Long id) {
		return new BaseResponse<>(itemService.getDescription(id));
	}

	@GetMapping("/calc/{itemId}")
	@Operation(summary = "상품 집계 API", description = "상품 집계 정보 (평균 별점, 리뷰 개수) 조회")
	public BaseResponse<ItemCalcRespVO> getItemCalc(@PathVariable("itemId") Long id) {
		return new BaseResponse<>(modelMapper.map(itemService.getItemCalc(id), ItemCalcRespVO.class));
	}

	@GetMapping
	@Operation(summary = "상품 목록(ID) 조회 API", description = "조건에 맞는 상품 목록 조회")
	public BaseResponse<ItemListRespVO> getItemList(
			@RequestParam(required = false) Integer bigCategoryId,
			@RequestParam(required = false) Integer middleCategoryId,
			@RequestParam(required = false) Integer smallCategoryId,
			@RequestParam(required = false) Integer detailCategoryId,
			@PageableDefault(size = 40) Pageable page) {

		return new BaseResponse<>(modelMapper.map(
				itemService.getItemList(
						ItemListReqDTO.toBuild(bigCategoryId, middleCategoryId, smallCategoryId, detailCategoryId),
						page), ItemListRespVO.class));
	}

	@PostMapping
	@Operation(summary = "상품 등록 API", description = "상품 정보 등록")
	public BaseResponse<?> createItem(@RequestBody ItemReqVO vo) {
		itemService.createItem(modelMapper.map(vo, ItemReqDTO.class));
		return new BaseResponse<>();
	}

	@GetMapping("/images/{itemId}")
	@Operation(summary = "상품 이미지 조회 API", description = "상품 이미지 조회")
	public BaseResponse<ItemImageListRespVO> itemWithImages(@PathVariable("itemId") Long itemId) {
		return new BaseResponse<>(modelMapper.map(
				itemService.getItemImages(itemId), ItemImageListRespVO.class));
	}

	@GetMapping("/thumbnail/{itemId}")
	@Operation(summary = "상품 썸네일 조회 API", description = "상품 이미지 중 썸네일 이미지만 조회")
	public BaseResponse<ItemThumbnailRespVO> itemThumbnail(@PathVariable("itemId") Long itemId) {
		return new BaseResponse<>(modelMapper.map(
				itemService.getItemThumbnail(itemId), ItemThumbnailRespVO.class));
	}

	@PostMapping("/recent/{itemId}")
	@Operation(summary = "최근 본 상품 내역 등록 API", description = "최근 본 상품 내역 등록 및 수정")
	public BaseResponse<String> createRecentViewItem(
			@RequestHeader(name = "Authorization", defaultValue = "") String accessToken,
			@PathVariable("itemId") Long itemId) {

		if(!StringUtils.hasText(accessToken)) {
			return new BaseResponse<>("비회원입니다. 최근 본 상품 내역을 저장하지 않습니다.");
		}

		return new BaseResponse<>(itemService
				.createRecentViewItem(itemId, jwtUtil.getUuidByAuthorization(accessToken)));
	}

	@GetMapping("/recent")
	@Operation(summary = "최근 본 상품 내역 조회 API", description = "최근 본 상품 내역 등록 및 수정")
	public BaseResponse<RecentViewListRespVO> getRecentViewItems(
			@RequestHeader(name = "Authorization", defaultValue = "") String accessToken,
			@PageableDefault(size = 10, sort = "viewDate", direction = Sort.Direction.DESC) Pageable page) {
		return new BaseResponse<>(modelMapper.map(
				itemService.getRecentViewItems(jwtUtil.getUuidByAuthorization(accessToken), page),
				RecentViewListRespVO.class));
	}

	@DeleteMapping("/recent")
	@Operation(summary = "최근 본 상품 내역 삭제 API", description = "최근 본 상품 내역 삭제 (복수 가능)")
	public BaseResponse<?> deleteRecentViewItem(
			@RequestHeader(name = "Authorization") String accessToken,
			@RequestBody DeleteRecentViewReqVO vo) {

		itemService.deleteRecentViewItems(
				jwtUtil.getUuidByAuthorization(accessToken),
				modelMapper.map(vo, DeleteRecentViewReqDTO.class));

		return new BaseResponse<>();
	}
}
