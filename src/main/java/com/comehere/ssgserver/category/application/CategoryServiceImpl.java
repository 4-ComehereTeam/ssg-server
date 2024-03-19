package com.comehere.ssgserver.category.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comehere.ssgserver.category.domain.BigCategory;
import com.comehere.ssgserver.category.dto.BigCategoryRespDTO;
import com.comehere.ssgserver.category.infrastructure.BigCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
	private final BigCategoryRepository bigCategoryRepository;

	@Override
	public List<BigCategory> findBigCategory() {
		return bigCategoryRepository.findAll();
	}
}
