package com.comehere.ssgserver.image.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comehere.ssgserver.image.domain.ItemWithImage;

public interface ItemWithImageRepository extends JpaRepository<ItemWithImage, Long> {
	@Query("select ii from ItemWithImage ii join fetch ii.image im where ii.item.id = :itemId")
	List<ItemWithImage> findByItemId(@Param("itemId") Long itemId);
}
