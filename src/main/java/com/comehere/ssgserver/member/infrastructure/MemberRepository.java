package com.comehere.ssgserver.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Boolean existsBySigninId(String signinId);
}
