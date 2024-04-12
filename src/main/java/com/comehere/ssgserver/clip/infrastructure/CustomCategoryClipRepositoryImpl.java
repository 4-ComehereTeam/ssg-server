package com.comehere.ssgserver.clip.infrastructure;

import static com.comehere.ssgserver.clip.domain.QCategoryClip.*;

import java.util.List;
import java.util.UUID;

import com.comehere.ssgserver.clip.dto.req.CategoryClipDeleteReqDTO;
import com.comehere.ssgserver.clip.dto.resp.CategoryClipGetRespDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomCategoryClipRepositoryImpl implements CustomCategoryClipRepository {
	private final JPAQueryFactory query;

	@Override
	public List<CategoryClipGetRespDTO> findCategoryClipGetRespDTOByUuid(UUID uuid) {
		return query.select(Projections.fields(CategoryClipGetRespDTO.class,
						categoryClip.id,
						categoryClip.bigCategoryId,
						categoryClip.middleCategoryId,
						categoryClip.smallCategoryId))
				.from(categoryClip)
				.where(categoryClip.uuid.eq(uuid))
				.fetch();
	}

	@Override
	public void deleteByUuidAndBigCategoryIdAndMiddleCategoryIdAndSmallCategoryId(UUID uuid,
			CategoryClipDeleteReqDTO dto) {
		query.delete(categoryClip)
				.where(categoryClip.uuid.eq(uuid),
						bigCategoryIdEq(dto.getBigCategoryId()),
						middleCategoryIdEq(dto.getMiddleCategoryId()),
						smallCategoryIdEq(dto.getSmallCategoryId()))
				.execute();
	}

	private BooleanExpression bigCategoryIdEq(Long bigCategoryId) {
		return bigCategoryId != null ? categoryClip.bigCategoryId.eq(bigCategoryId) :
				categoryClip.bigCategoryId.isNull();
	}

	private BooleanExpression middleCategoryIdEq(Long middleCategoryId) {
		return middleCategoryId != null ? categoryClip.middleCategoryId.eq(middleCategoryId) :
				categoryClip.middleCategoryId.isNull();
	}

	private BooleanExpression smallCategoryIdEq(Long smallCategoryId) {
		return smallCategoryId != null ? categoryClip.smallCategoryId.eq(smallCategoryId) :
				categoryClip.smallCategoryId.isNull();
	}

}
