package com.comehere.ssgserver.member.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Boolean existsBySignInId(String signInId);

	Optional<Member> findBySignInId(String signInId);
}
