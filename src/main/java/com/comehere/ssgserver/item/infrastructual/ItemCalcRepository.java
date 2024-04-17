package com.comehere.ssgserver.item.infrastructual;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.item.domain.ItemCalc;

public interface ItemCalcRepository extends JpaRepository<ItemCalc, Long> {
	Optional<ItemCalc> findByItemId(Long itemId);
}
