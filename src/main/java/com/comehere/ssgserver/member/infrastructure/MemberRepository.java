package com.comehere.ssgserver.member.infrastructure;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository {

	Boolean existsBySigninId(String signInId);

	Boolean existsByEmail(String email);

	<T> Optional<T> findByEmail(String email, Class<T> type);

	Optional<Member> findBySigninId(String signInId);

	Optional<Member> findByUuid(UUID uuid);

	Optional<Member> findByEmailAndName(String email, String name);
}
