package com.comehere.ssgserver.member.infrastructure;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository {

	Boolean existsBySigninId(String signInId);

	<T> Optional<T> findByEmail(String email, Class<T> type);

	Boolean existsByEmail(String email);

	Optional<Member> findBySigninId(String signInId);

	Optional<Member> findByUuid(UUID uuid);

	Member findByEmail(String email);

}
