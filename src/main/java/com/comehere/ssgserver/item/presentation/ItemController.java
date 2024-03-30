package com.comehere.ssgserver.item.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.item.application.ItemService;
import com.comehere.ssgserver.item.dto.req.ItemListReqDTO;
import com.comehere.ssgserver.item.dto.resp.ItemDetailRespDTO;
import com.comehere.ssgserver.item.vo.req.ItemReqVO;
import com.comehere.ssgserver.item.vo.resp.ItemCalcRespVO;
import com.comehere.ssgserver.item.vo.resp.ItemListRespVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
@Tag(name = "items", description = "상품 관리 컨트롤러")
public class ItemController {
	private final ItemService itemService;
	private final ModelMapper modelMapper;

	@GetMapping("/detail/{itemId}")
	@Operation(summary = "상품 기본 정보 API", description = "상품 기본 정보 (상품명, 가격) 조회")
	public ItemDetailRespDTO itemDetail(@PathVariable("itemId") Long id) {
		return itemService.getItemDetail(id);
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

		return new BaseResponse<>(ItemListRespVO.toBuild(
				itemService.getItemList(
						ItemListReqDTO.toBuild(bigCategoryId, middleCategoryId, smallCategoryId, detailCategoryId),
						page)));
	}

	@PostMapping
	@Operation(summary = "상품 등록 API", description = "상품 정보 등록")
	public BaseResponse<?> createItem(@RequestBody ItemReqVO vo) {
		itemService.createItem(vo);
		return new BaseResponse<>();
	}
}
