package com.comehere.ssgserver.purchase.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.purchase.domain.Address;

public interface AddressRepostitory extends JpaRepository<Address, Long> {
}
