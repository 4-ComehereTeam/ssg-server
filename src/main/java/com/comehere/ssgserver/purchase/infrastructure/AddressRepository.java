package com.comehere.ssgserver.purchase.infrastructure;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.purchase.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long>, CustomAddressRepository {
	List<Address> findAllByUuid(UUID uuid);

	// 해당 주소 삭제
	@Modifying
	@Transactional
	@Query("DELETE FROM Address a WHERE a.id = :id AND a.uuid = :uuid")
	void deleteAddressById(@Param("id") Long id, @Param("uuid") UUID uuid);
}
