package com.comehere.ssgserver.item.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.item.application.ItemService;
import com.comehere.ssgserver.item.dto.ItemDetailRespDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class ItemController {
	private final ItemService itemService;

	@GetMapping("/{id}")
	public ItemDetailRespDTO itemDetail(@PathVariable("id") Long id) {
		return itemService.findById(id);
	}
}
