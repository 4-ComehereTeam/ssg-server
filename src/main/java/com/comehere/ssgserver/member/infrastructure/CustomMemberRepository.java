package com.comehere.ssgserver.member.infrastructure;

import java.util.UUID;

public interface CustomMemberRepository {

	// 회원 탈퇴
	Long resignMember(String signinId);

	// 배송 정보 삭제
	Long deleteAddress(UUID uuid);

	// 동의 정보 삭제
	Long deldteAgree(String email);
}
