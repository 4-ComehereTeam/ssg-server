package com.comehere.ssgserver.image.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comehere.ssgserver.image.domain.ItemImage;

public interface ItemImageRepository extends JpaRepository<ItemImage, Long> {
	List<ItemImage> findByItemId(Long itemId);

	@Query("select ii from ItemImage ii where ii.itemId = :itemId and ii.thumbnail = true")
	ItemImage findThumbnail(@Param("itemId") Long itemId);
}
