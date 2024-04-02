package com.comehere.ssgserver.review.infrastructure;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.comehere.ssgserver.review.dto.resp.ReviewImageListDTO;

public interface CustomReviewRepository {
	Slice<ReviewImageListDTO> getReviewImageList(String itemCode, Pageable page);
}
