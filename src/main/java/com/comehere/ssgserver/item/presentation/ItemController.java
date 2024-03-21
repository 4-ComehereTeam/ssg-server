package com.comehere.ssgserver.item.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.item.application.ItemService;
import com.comehere.ssgserver.item.dto.ItemDetailRespDTO;
import com.comehere.ssgserver.item.vo.ItemListReqVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
@Tag(name = "items", description = "상품 관리 컨트롤러")
public class ItemController {
	private final ItemService itemService;

	@GetMapping("/{id}")
	@Operation(summary = "상품 상세정보 API", description = "상품 상세정보 조회")
	public ItemDetailRespDTO itemDetail(@PathVariable("id") Long id) {
		return itemService.getItemDetail(id);
	}

	@GetMapping
	@Operation(summary = "상품 목록(ID) 조회 API", description = "조건에 맞는 상품 목록 조회")
	public List<Long> getItemList(ItemListReqVO vo) {
		return itemService.getItemList(vo);
	}
}
