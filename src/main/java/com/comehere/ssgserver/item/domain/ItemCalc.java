package com.comehere.ssgserver.item.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ItemCalc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long itemId;

	private Double averageStar;

	private Long reviewCount;

	@Builder
	public ItemCalc(Double averageStar, Long reviewCount) {
		this.averageStar = averageStar;
		this.reviewCount = reviewCount;
	}
}
