package com.comehere.ssgserver.option.infrastructure;

import java.util.List;

import com.comehere.ssgserver.option.domain.ItemOption;

public interface CustomOptionRepository {
	List<ItemOption> findSize(Long itemId, Long colorId);

	List<ItemOption> findEtc(Long itemId, Long colorId, Long sizeId);

	Long findOptionId(Long reviewId);
}
