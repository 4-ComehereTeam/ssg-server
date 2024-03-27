package com.comehere.ssgserver.option.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comehere.ssgserver.option.domain.Color;
import com.comehere.ssgserver.option.domain.ItemOption;

public interface ItemOptionRepository extends JpaRepository<ItemOption, Long>{
	@Query("select io from ItemOption io left join fetch io.color "
			+ "left join fetch io.size "
			+ "left join fetch io.etc "
			+ "where io.item.id = :itemId")
	List<ItemOption> findOptions(@Param("itemId") Long itemId);

	ItemOption findFirstByItemId(Long id);
}
