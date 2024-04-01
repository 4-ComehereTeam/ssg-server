package com.comehere.ssgserver.purchase.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.purchase.domain.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	Optional<Purchase> findByPurchaseCodeAndUuid(String purchaseCode, UUID uuid);

	List<Purchase> findByUuid(UUID uuid);
}
