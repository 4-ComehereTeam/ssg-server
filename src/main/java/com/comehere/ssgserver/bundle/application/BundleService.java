package com.comehere.ssgserver.bundle.application;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.comehere.ssgserver.bundle.domain.Bundle;
import com.comehere.ssgserver.bundle.vo.BundleReqVO;

public interface BundleService {
	void createBundle(BundleReqVO vo);

	Page<Bundle> getBundleList(Pageable page);
}
