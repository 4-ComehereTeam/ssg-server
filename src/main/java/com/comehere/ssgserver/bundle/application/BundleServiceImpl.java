package com.comehere.ssgserver.bundle.application;

import static com.comehere.ssgserver.common.response.BaseResponseStatus.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.brand.domain.Brand;
import com.comehere.ssgserver.brand.infrastructure.BrandRepository;
import com.comehere.ssgserver.bundle.domain.Bundle;
import com.comehere.ssgserver.bundle.domain.BundleWithItem;
import com.comehere.ssgserver.bundle.dto.req.CreateBundleReqDTO;
import com.comehere.ssgserver.bundle.dto.resp.BundleItemRespDTO;
import com.comehere.ssgserver.bundle.dto.resp.BundleListRespDTO;
import com.comehere.ssgserver.bundle.dto.resp.BundleRespDTO;
import com.comehere.ssgserver.bundle.infrastructure.BundleRepository;
import com.comehere.ssgserver.bundle.infrastructure.BundleWithItemRepository;
import com.comehere.ssgserver.common.exception.BaseException;
import com.comehere.ssgserver.common.response.BaseResponseStatus;
import com.comehere.ssgserver.item.infrastructual.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BundleServiceImpl implements BundleService {
	private final BundleRepository bundleRepository;
	private final BundleWithItemRepository bundleWithItemRepository;
	private final ItemRepository itemRepository;
	private final BrandRepository brandRepository;

	@Override
	public void createBundle(CreateBundleReqDTO dto) {
		Bundle bundle = saveBundle(dto);
		saveBundleList(dto, bundle);
	}

	@Override
	@Transactional(readOnly = true)
	public BundleListRespDTO getBundleList(Integer categoryId, Pageable page) {
		return BundleListRespDTO.toBuild(bundleRepository.getBundleList(categoryId, page));
	}

	@Override
	public Bundle updateBundleStatus(Long id) {
		Bundle bundle = bundleRepository.findById(id)
				.orElseThrow(() -> new BaseException(BUNDLE_NOT_FOUND));

		Bundle.closeBundle(bundle);
		return bundle;
	}

	@Override
	public BundleRespDTO getBundleDetail(Long id) {
		Bundle bundle = bundleRepository.findById(id)
				.orElseThrow(() -> new BaseException(BUNDLE_NOT_FOUND));

		return BundleRespDTO.builder()
				.bundleId(id)
				.name(bundle.getName())
				.minPrice(bundle.getMinPrice())
				.build();
	}

	@Override
	public BundleItemRespDTO getBundleItemList(Long bundleId) {
		return BundleItemRespDTO.builder()
				.bundleId(bundleId)
				.items(bundleWithItemRepository.findByBundleId(bundleId))
				.build();
	}

	private Bundle saveBundle(CreateBundleReqDTO dto) {
		return bundleRepository.save(Bundle.builder()
				.name(dto.getName())
				.minPrice(itemRepository.findMinPrice(dto.getItems()))
				.finishDate(dto.getFinishDate())
				.imageUrl(dto.getImage())
				.alt(dto.getAlt())
				.status(true)
				.build());
	}

	private void saveBundleList(CreateBundleReqDTO dto, Bundle bundle) {
		itemRepository.findItemList(dto.getItems()).stream()
				.forEach(i -> bundleWithItemRepository.save(BundleWithItem.builder()
						.bundle(bundle)
						.item(i)
						.build()));
	}
}
