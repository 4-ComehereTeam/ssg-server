package com.comehere.ssgserver.purchase.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.purchase.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long>, CustomAddressRepository {
	List<Address> findAllByUuid(UUID uuid);

	Optional<Address> findByIdAndUuid(Long id, UUID uuid);
}
