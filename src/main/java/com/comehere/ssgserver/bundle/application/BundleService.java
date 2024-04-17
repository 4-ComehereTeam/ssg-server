package com.comehere.ssgserver.bundle.application;

import org.springframework.data.domain.Pageable;

import com.comehere.ssgserver.bundle.domain.Bundle;
import com.comehere.ssgserver.bundle.dto.req.CreateBundleReqDTO;
import com.comehere.ssgserver.bundle.dto.resp.BundleItemRespDTO;
import com.comehere.ssgserver.bundle.dto.resp.BundleListRespDTO;
import com.comehere.ssgserver.bundle.dto.resp.BundleInfoRespDTO;

public interface BundleService {
	void createBundle(CreateBundleReqDTO dto);

	BundleListRespDTO getBundleList(Integer categoryId, Pageable page);

	Bundle updateBundleStatus(Long id);

	BundleInfoRespDTO getBundleDetail(Long id);

	BundleItemRespDTO getBundleItemList(Long bundleId);
}
