package com.comehere.ssgserver.member.infrastructure;

import java.time.LocalDateTime;

import com.comehere.ssgserver.member.domain.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomMemberRepositoryImpl implements CustomMemberRepository {

	private final JPAQueryFactory query;

	@Override
	public Long ResignMember(String signinId) {

		Long result = query.update(QMember.member)
				.set(QMember.member.resignCount, QMember.member.resignCount.add(1))
				.set(QMember.member.status, (short)-1)
				.set(QMember.member.resignTime, LocalDateTime.now())
				.where(QMember.member.signinId.eq(signinId),
						QMember.member.status.eq((short)1))
				.execute();

		return result;
	}
}
