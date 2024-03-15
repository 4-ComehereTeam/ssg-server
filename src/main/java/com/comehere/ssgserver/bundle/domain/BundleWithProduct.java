package com.comehere.ssgserver.bundle.domain;

import org.hibernate.loader.ast.spi.SingleUniqueKeyEntityLoader;
import org.springframework.data.util.Lazy;

import com.comehere.ssgserver.item.domain.Item;
import com.comehere.ssgserver.vender.domain.Vender;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.metamodel.IdentifiableType;
import lombok.Getter;

@Entity
@Getter
public class BundleWithProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bundle_id")
	private Bundle bundle;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
}
