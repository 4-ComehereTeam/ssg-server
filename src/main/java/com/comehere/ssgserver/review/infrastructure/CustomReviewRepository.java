package com.comehere.ssgserver.review.infrastructure;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.comehere.ssgserver.review.dto.resp.ReviewImageListDTO;
import com.comehere.ssgserver.review.dto.resp.ReviewSummaryDTO;

public interface CustomReviewRepository {
	Slice<ReviewImageListDTO> getReviewImageList(String itemCode, Pageable page);
}
