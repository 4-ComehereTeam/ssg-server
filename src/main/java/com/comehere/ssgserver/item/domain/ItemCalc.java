package com.comehere.ssgserver.item.domain;

import org.hibernate.metamodel.mapping.internal.InFlightEntityMappingType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class ItemCalc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;

	private Double AverageStar;

	private Long reviewCount;

}
