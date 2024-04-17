package com.comehere.ssgserver.option.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comehere.ssgserver.option.domain.ItemOption;

public interface ItemOptionRepository extends JpaRepository<ItemOption, Long>, CustomOptionRepository {

	ItemOption findFirstByItemId(Long itemId);

	@Query("select io from ItemOption io left join fetch io.color where io.item.id = :itemId")
	List<ItemOption> findColorByItemId(@Param("itemId") Long itemId);
}
