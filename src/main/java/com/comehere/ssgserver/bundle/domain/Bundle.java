package com.comehere.ssgserver.bundle.domain;

import java.time.LocalDate;

import com.comehere.ssgserver.brand.domain.Brand;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
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

	@Column(nullable = false)
	private Integer categoryId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false)
	private Long minPrice;

	@Column(nullable = false)
	private LocalDate finishDate;

	@Column(columnDefinition = "TINYINT")
	private Boolean status;

	@Builder
	public Bundle(Integer categoryId, Brand brand, String name, Long minPrice, LocalDate finishDate, Boolean status) {
		this.categoryId = categoryId;
		this.brand = brand;
		this.name = name;
		this.minPrice = minPrice;
		this.finishDate = finishDate;
		this.status = status;
	}

	public static void closeBundle(Bundle bundle) {
		bundle.status = false;
	}
}
