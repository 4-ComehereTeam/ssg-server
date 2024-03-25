package com.comehere.ssgserver.item.presentation;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.item.application.ItemService;
import com.comehere.ssgserver.item.dto.ItemCalcRespDTO;
import com.comehere.ssgserver.item.dto.ItemDetailRespDTO;
import com.comehere.ssgserver.item.vo.ItemListReqVO;
import com.comehere.ssgserver.item.dto.ItemListRespDTO;
import com.comehere.ssgserver.item.vo.ItemReqVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
@Tag(name = "items", description = "상품 관리 컨트롤러")
public class ItemController {
	private final ItemService itemService;

	@GetMapping("/detail/{id}")
	@Operation(summary = "상품 기본 정보 API", description = "상품 기본 정보 (상품명, 가격) 조회")
	public ItemDetailRespDTO itemDetail(@PathVariable("id") Long id) {
		return itemService.getItemDetail(id);
	}

	@GetMapping("/description/{id}")
	@Operation(summary = "상품 상세 정보 API", description = "상품 상세 정보 조회")
	public String getItemDescription(@PathVariable("id") Long id) {
		return itemService.getDescription(id);
	}

	@GetMapping("/calc/{id}")
	@Operation(summary = "상품 집계 API", description = "상품 집계 정보 (평균 별점, 리뷰 개수) 조회")
	public ItemCalcRespDTO getItemCalc(@PathVariable("id") Long id) {
		return itemService.getItemCalc(id);
	}

	@GetMapping
	@Operation(summary = "상품 목록(ID) 조회 API", description = "조건에 맞는 상품 목록 조회")
	public ItemListRespDTO getItemList(ItemListReqVO vo,
			@PageableDefault(size = 40) Pageable page) {
		return itemService.getItemList(vo, page);
	}

	@PostMapping
	@Operation(summary = "상품 등록 API", description = "상품 정보 등록")
	public BaseResponse<?> createItem(@RequestBody ItemReqVO vo) {
		itemService.createItem(vo);
		return new BaseResponse<>();
	}
}
