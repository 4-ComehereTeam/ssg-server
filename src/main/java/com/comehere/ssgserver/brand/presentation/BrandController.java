package com.comehere.ssgserver.brand.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.brand.application.BrandService;
import com.comehere.ssgserver.brand.dto.BrandRespDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BrandController {
	private final BrandService brandService;

	@GetMapping("/items/brand/{id}")
	public BrandRespDTO getBrand(@PathVariable("id") Long itemId) {
		return brandService.getBrand(itemId);
	}
}
