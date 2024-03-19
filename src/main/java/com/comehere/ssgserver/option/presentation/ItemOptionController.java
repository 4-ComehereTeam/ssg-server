package com.comehere.ssgserver.option.presentation;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.option.application.ItemOptionService;
import com.comehere.ssgserver.option.domain.ItemOption;
import com.comehere.ssgserver.option.dto.ItemOptionRespDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ItemOptionController {
	private final ItemOptionService itemOptionService;

	@GetMapping("/items/options/{id}")
	public ItemOptionRespDTO itemOption(@PathVariable("id") Long itemId) {
		return itemOptionService.findByItemId(itemId);
	}
}
