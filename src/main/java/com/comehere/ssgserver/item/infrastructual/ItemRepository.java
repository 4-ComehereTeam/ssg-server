package com.comehere.ssgserver.item.infrastructual;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.item.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long>, CustomItemRepository {
}
