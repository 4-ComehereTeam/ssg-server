package com.comehere.ssgserver.option.infrastructure;

import java.util.List;

import com.comehere.ssgserver.option.domain.ItemOption;

public interface CustomOptionRepository {
	List<ItemOption> findSize(Long itemId, Long colorId);
}
