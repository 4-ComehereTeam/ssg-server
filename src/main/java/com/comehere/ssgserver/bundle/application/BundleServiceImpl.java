package com.comehere.ssgserver.bundle.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.comehere.ssgserver.bundle.domain.Bundle;
import com.comehere.ssgserver.bundle.domain.BundleWithItem;
import com.comehere.ssgserver.bundle.infrastructure.BundleRepository;
import com.comehere.ssgserver.bundle.infrastructure.BundleWithItemRepository;
import com.comehere.ssgserver.bundle.vo.BundleReqVO;
import com.comehere.ssgserver.item.infrastructual.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BundleServiceImpl implements BundleService {
	private final BundleRepository bundleRepository;
	private final BundleWithItemRepository bundleWithItemRepository;
	private final ItemRepository itemRepository;

	@Override
	public void createBundle(BundleReqVO vo) {
		Bundle bundle = bundleRepository.save(Bundle.builder()
				.name(vo.getName())
				.minPrice(itemRepository.findMinPrice(vo.getItems()))
				.status(true)
				.build());

		itemRepository.findItemList(vo.getItems()).stream()
				.forEach(i -> bundleWithItemRepository.save(BundleWithItem.builder()
								.bundle(bundle)
								.item(i)
						.build()));
	}

	@Override
	public Page<Bundle> getBundleList(Pageable page) {
		return bundleRepository.getBundleList(page);
	}
}
