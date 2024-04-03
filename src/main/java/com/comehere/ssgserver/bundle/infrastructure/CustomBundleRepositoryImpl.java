package com.comehere.ssgserver.bundle.infrastructure;

import static com.comehere.ssgserver.bundle.domain.QBundle.*;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import com.comehere.ssgserver.bundle.domain.QBundle;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomBundleRepositoryImpl implements CustomBundleRepository {
	private final JPQLQueryFactory query;

	@Override
	public Slice<Long> getBundleList(Integer categoryId, Pageable page) {
		// query.select(bundle.id)
		// 		.from(bundle)
		// 		.where()


		return null;
	}

	/*private BooleanExpression categoryIdEq(Integer ) {

	}*/
}
