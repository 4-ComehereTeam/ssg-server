package com.comehere.ssgserver.member.infrastructure;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository {

	Boolean existsBySigninId(String signInId);

	Boolean existsByEmail(String email);

	Optional<Member> findByEmail(String email);

	Optional<Member> findBySigninId(String signInId);

	Optional<Member> findByUuid(UUID uuid);

	Optional<Member> findByEmailAndName(String email, String name);

	@Transactional
	@Modifying
	@Query("update Member m set m.updateAt = CURRENT_TIMESTAMP where m.uuid = :uuid")
	void updateUpdateAtByUuid(@Param("uuid") UUID uuid);
}
