package com.comehere.ssgserver.brand.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.brand.domain.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
