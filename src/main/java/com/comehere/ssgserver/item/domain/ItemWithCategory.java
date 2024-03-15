package com.comehere.ssgserver.item.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class ItemWithCategory {
	@Id
	@GeneratedValue
	private Long id;

	private Integer bigCategory;

	private Integer middleCategory;

	private Integer smallCategory;

	private Integer detailCategory;
}
