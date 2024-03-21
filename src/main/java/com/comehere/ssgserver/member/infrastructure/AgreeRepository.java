package com.comehere.ssgserver.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.member.domain.Agree;

public interface AgreeRepository extends JpaRepository<Agree, Long> {
}
