package com.comehere.ssgserver.item.infrastructual;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comehere.ssgserver.item.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long>, CustomItemRepository {
	@Query("select min(i.price) from Item i where i.id in :items")
	Long findMinPrice(@Param("items") List<Long> items);

	@Query("select i from Item i where i.id in :items")
	List<Item> findItemList(@Param("items") List<Long> items);
}
