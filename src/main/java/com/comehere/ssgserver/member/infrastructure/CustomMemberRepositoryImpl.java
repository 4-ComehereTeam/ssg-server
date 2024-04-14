package com.comehere.ssgserver.member.infrastructure;

import java.time.LocalDateTime;
import java.util.UUID;

import com.comehere.ssgserver.member.domain.QAgree;
import com.comehere.ssgserver.member.domain.QMember;
import com.comehere.ssgserver.member.dto.req.FindPasswordReqDTO;
import com.comehere.ssgserver.purchase.domain.QAddress;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomMemberRepositoryImpl implements CustomMemberRepository {

	private final JPAQueryFactory query;

	//로그인 상태 : 비밀번호 변경
	@Override
	public Long memberUpdatePassword(UUID uuid, String newPassword) {

		return query.update(QMember.member)
				.set(QMember.member.password, newPassword)
				.where(QMember.member.uuid.eq(uuid))
				.execute();
	}

	@Override
	public Long updatePassword(FindPasswordReqDTO findPasswordReqDto) {

		return query.update(QMember.member)
				.set(QMember.member.password, findPasswordReqDto.getNewPassword())
				.where(QMember.member.email.eq(findPasswordReqDto.getEmail()),
						QMember.member.name.eq(findPasswordReqDto.getName()))
				.execute();
	}

	//이메일 변경
	@Override
	public Long updateEmail(UUID uuid, String newEmail) {

		return query.update(QMember.member)
				.set(QMember.member.email, newEmail)
				.where(QMember.member.uuid.eq(uuid))
				.execute();
	}

	//휴대폰 번호 변경
	@Override
	public Long updatePhone(UUID uuid, String newPhone) {

		return query.update(QMember.member)
				.set(QMember.member.phone, newPhone)
				.where(QMember.member.uuid.eq(uuid))
				.execute();
	}

	//회원 탈퇴
	@Override
	public Long ResignMember(String signinId) {

		return query.update(QMember.member)
				.set(QMember.member.resignCount, QMember.member.resignCount.add(1))
				.set(QMember.member.status, (short)-1)
				.set(QMember.member.resignTime, LocalDateTime.now())
				.set(QMember.member.signinId, " ")
				.where(QMember.member.signinId.eq(signinId),
						QMember.member.status.eq((short)1))
				.execute();
	}

	//주소 삭제
	@Override
	public Long deleteAddress(UUID uuid) {

		return query.delete(QAddress.address1)
				.where(QAddress.address1.uuid.eq(uuid))
				.execute();
	}

	//약관 동의 삭제
	@Override
	public Long deleteAgree(String email) {

		return query.delete(QAgree.agree)
				.where(QAgree.agree.email.eq(email))
				.execute();
	}

	@Override
	public void updateDormantMember() {

		query.update(QMember.member)
				.set(QMember.member.status, (short)0)
				.where(QMember.member.updateAt.lt(LocalDateTime.now().minusYears(1)),
						QMember.member.status.eq((short)1))
				.execute();
	}
}
