package com.comehere.ssgserver.bundle.infrastructure;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CustomBundleRepository {
	Slice<Long> getBundleList(Integer categoryId, Pageable page);
}
