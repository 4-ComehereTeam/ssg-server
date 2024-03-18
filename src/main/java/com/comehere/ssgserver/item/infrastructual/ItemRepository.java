package com.comehere.ssgserver.item.infrastructual;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.comehere.ssgserver.item.domain.Item;
import com.comehere.ssgserver.item.dto.ItemDetailRespDTO;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
