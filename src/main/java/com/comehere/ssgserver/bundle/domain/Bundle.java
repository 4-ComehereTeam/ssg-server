package com.comehere.ssgserver.bundle.domain;

import com.comehere.ssgserver.brand.domain.Brand;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Bundle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "brand_id")
	// private Brand brand;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false)
	private Long minPrice;

	@Column(columnDefinition = "TINYINT")
	private Boolean status;

	@Builder
	public Bundle(String name, Long minPrice, Boolean status) {
		this.name = name;
		this.minPrice = minPrice;
		this.status = status;
	}
}
