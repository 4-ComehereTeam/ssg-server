package com.comehere.ssgserver.bundle.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.bundle.domain.Bundle;

public interface BundleRepository extends JpaRepository<Bundle, Long> {
}
