package com.comehere.ssgserver.common.batch;

import java.util.List;
import java.util.function.Function;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuerydslPagingItemReader<T> implements ItemReader<T> {

	private final JPAQueryFactory jpaQueryFactory;
	private final int pageSize;
	private long offset = 0;
	private Function<JPAQueryFactory, JPAQuery<T>> queryFunction;

	public QuerydslPagingItemReader(JPAQueryFactory jpaQueryFactory, int pageSize, Function<JPAQueryFactory, JPAQuery<T>> queryFunction) {
		this.jpaQueryFactory = jpaQueryFactory;
		this.pageSize = pageSize;
		this.queryFunction = queryFunction;
	}

	@Override
	@Transactional(readOnly = true)
	public T read() {
		JPAQuery<T> query = queryFunction.apply(jpaQueryFactory)
				.offset(offset)
				.limit(pageSize);

		List<T> results = query.fetch();

		if (!results.isEmpty()) {
			offset += results.size();
			log.info("읽은 데이터 개수: {}", results.size()); // 로그로 결과 개수 출력
			return results.get(0); // 이 예제에서는 한 번에 하나의 엔티티만 반환하도록 설정
		} else {
			return null; // 더 이상 읽을 데이터가 없음을 의미
		}
	}
}

