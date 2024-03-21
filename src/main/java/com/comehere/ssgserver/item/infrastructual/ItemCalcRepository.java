package com.comehere.ssgserver.item.infrastructual;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comehere.ssgserver.item.domain.ItemCalc;

public interface ItemCalcRepository extends JpaRepository<ItemCalc, Long> {
}
