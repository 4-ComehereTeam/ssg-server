package com.comehere.ssgserver.member.infrastructure;

import java.util.UUID;

public interface CustomMemberRepository {

	//비밀번호 변경
	Long updatePassword(UUID uuid, String newPassword);

	//휴대폰 번호 변경
	Long updatePhone(UUID uuid, String phone);

	//이메일 변경
	Long updateEmail(UUID uuid, String email);

	//회원 탈퇴
	Long ResignMember(String signinId);

	//주소 삭제
	Long deleteAddress(UUID uuid);

	//약관 동의 삭제
	Long deleteAgree(String email);
}
