package com.comehere.ssgserver.item.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String code;

	@Column(columnDefinition = "LONGTEXT")
	private String description;

	private Long price;

	private Integer discountRate;

	@Column(columnDefinition = "TINYINT")
	private Short status;

	private Integer addGroup;

}
