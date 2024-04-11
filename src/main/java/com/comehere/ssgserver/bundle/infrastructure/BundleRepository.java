package com.comehere.ssgserver.bundle.infrastructure;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.comehere.ssgserver.bundle.domain.Bundle;

public interface BundleRepository extends JpaRepository<Bundle, Long>, CustomBundleRepository {
	@Query("select b from Bundle b where b.status = true")
	Page<Bundle> getBundleList(Pageable page);

	@Modifying
	@Query("update Bundle b set b.status = false where b.finishDate < current_date")
	void updateBundleStatus();
}
