package com.comehere.ssgserver.member.infrastructure;

import static com.comehere.ssgserver.member.domain.QMember.*;

import java.time.LocalDateTime;
import java.util.UUID;

import com.comehere.ssgserver.member.domain.QAgree;
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

		return query.update(member)
				.set(member.password, newPassword)
				.where(member.uuid.eq(uuid))
				.execute();
	}

	@Override
	public Long updatePassword(FindPasswordReqDTO findPasswordReqDto) {

		return query.update(member)
				.set(member.password, findPasswordReqDto.getNewPassword())
				.where(member.email.eq(findPasswordReqDto.getEmail()),
						member.name.eq(findPasswordReqDto.getName()))
				.execute();
	}

	//이메일 변경
	@Override
	public Long updateEmail(UUID uuid, String newEmail) {

		return query.update(member)
				.set(member.email, newEmail)
				.where(member.uuid.eq(uuid))
				.execute();
	}

	//휴대폰 번호 변경
	@Override
	public Long updatePhone(UUID uuid, String newPhone) {

		return query.update(member)
				.set(member.phone, newPhone)
				.where(member.uuid.eq(uuid))
				.execute();
	}

	//회원 탈퇴
	@Override
	public Long ResignMember(String signinId) {

		return query.update(member)
				.set(member.resignCount, member.resignCount.add(1))
				.set(member.status, (short)-1)
				.set(member.resignTime, LocalDateTime.now())
				.set(member.signinId, " ")
				.where(member.signinId.eq(signinId),
						member.status.eq((short)1))
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

		query.update(member)
				.set(member.status, (short)0)
				.where(member.updateAt.lt(LocalDateTime.now().minusYears(1)),
						member.status.eq((short)1))
				.execute();
	}
}
