package com.comehere.ssgserver.cart.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.cart.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>, CustomCartRepository {
}
