package com.comehere.ssgserver.member.infrastructure;

import java.time.LocalDateTime;
import java.util.UUID;

import com.comehere.ssgserver.member.domain.QAgree;
import com.comehere.ssgserver.member.domain.QMember;
import com.comehere.ssgserver.purchase.domain.QAddress;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomMemberRepositoryImpl implements CustomMemberRepository {

	private final JPAQueryFactory query;

	@Override
	public Long updatePassword(UUID uuid, String newPassword) {

		return query.update(QMember.member)
				.set(QMember.member.password, newPassword)
				.where(QMember.member.uuid.eq(uuid))
				.execute();
	}

	@Override
	public Long updateEmail(UUID uuid, String newEmail) {

		return query.update(QMember.member)
				.set(QMember.member.email, newEmail)
				.where(QMember.member.uuid.eq(uuid))
				.execute();
	}

	@Override
	public Long updatePhone(UUID uuid, String newPhone) {

		return query.update(QMember.member)
				.set(QMember.member.phone, newPhone)
				.where(QMember.member.uuid.eq(uuid))
				.execute();
	}

	@Override
	public Long ResignMember(String signinId) {

		Long result = query.update(QMember.member)
				.set(QMember.member.resignCount, QMember.member.resignCount.add(1))
				.set(QMember.member.status, (short)-1)
				.set(QMember.member.resignTime, LocalDateTime.now())
				.set(QMember.member.signinId, " ")
				.where(QMember.member.signinId.eq(signinId),
						QMember.member.status.eq((short)1))
				.execute();

		return result;
	}

	@Override
	public Long deleteAddress(UUID uuid) {

		return query.delete(QAddress.address1)
				.where(QAddress.address1.uuid.eq(uuid))
				.execute();
	}

	@Override
	public Long deleteAgree(String email) {

		return query.delete(QAgree.agree)
				.where(QAgree.agree.email.eq(email))
				.execute();
	}
}
