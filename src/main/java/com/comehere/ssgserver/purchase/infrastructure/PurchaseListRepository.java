package com.comehere.ssgserver.purchase.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.purchase.domain.PurchaseList;

public interface PurchaseListRepository extends JpaRepository<PurchaseList, Long>, CustomPurchaseListRepository {
	boolean existsByPurchaseIdAndItemOptionId(Long purchaseId, Long itemOptionId);

	Optional<PurchaseList> findByIdAndPurchaseId(Long purchaseListId, Long id);

	List<PurchaseList> findByPurchaseId(Long id);
}
