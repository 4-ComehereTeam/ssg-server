package com.comehere.ssgserver.item.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Item { // 필드 설정 추가 + @Builder
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	private String name;

	private String code;

	@Column(columnDefinition = "LONGTEXT", nullable = false)
	private String description;

	@Column(nullable = false)
	private Long price;

	private Integer discountRate;

	@Column(columnDefinition = "TINYINT")
	private Short status;

	private Integer addGroup;

}
