package com.comehere.ssgserver.item.infrastructual;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comehere.ssgserver.item.domain.ItemCalc;

public interface ItemCalcRepository extends JpaRepository<ItemCalc, Long> {
	@Query("select ic from ItemCalc ic join fetch ic.item i where i.id = :itemId")
	Optional<ItemCalc> findByItemId(@Param("itemId") Long itemId);
}
