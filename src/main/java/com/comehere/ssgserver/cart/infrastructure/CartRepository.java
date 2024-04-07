package com.comehere.ssgserver.cart.infrastructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comehere.ssgserver.cart.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>, CustomCartRepository {

	// 해당 상품이 장바구니에 담겨있는 개수 조회
	@Query("SELECT c.itemCount FROM Cart c WHERE c.itemOptionId = :itemOptionId AND c.uuid = :uuid")
	Integer findByItemOptionIdAndUuid(@Param("itemOptionId") Long itemOptionId, @Param("uuid") UUID uuid);
}
