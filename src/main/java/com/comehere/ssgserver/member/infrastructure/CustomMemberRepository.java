package com.comehere.ssgserver.member.infrastructure;

import java.util.UUID;

public interface CustomMemberRepository {

	Long ResignMember(String signinId);

	Long deleteAddress(UUID uuid);

	Long deleteAgree(String email);
}
