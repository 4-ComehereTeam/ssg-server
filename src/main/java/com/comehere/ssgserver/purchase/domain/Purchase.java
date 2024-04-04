package com.comehere.ssgserver.purchase.domain;

import static org.springframework.data.jpa.domain.AbstractPersistable_.*;

import java.util.UUID;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.comehere.ssgserver.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE purchase SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Purchase extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, updatable = false)
	private String purchaseCode;

	@Column(updatable = false)
	private UUID uuid;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String addressNickname;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String detailAddress;

	@Column(nullable = false)
	private String zipcode;

	@Column(columnDefinition = "Longtext")
	private String requestMessage;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PurchaseStatus status;

	private Boolean deleted;

	@Builder

	public Purchase(Long id, String purchaseCode, UUID uuid, String name, String phone, String email,
			String addressNickname, String address, String detailAddress, String zipcode, String requestMessage,
			PurchaseStatus status, Boolean deleted) {
		this.id = id;
		this.purchaseCode = purchaseCode;
		this.uuid = uuid;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.addressNickname = addressNickname;
		this.address = address;
		this.detailAddress = detailAddress;
		this.zipcode = zipcode;
		this.requestMessage = requestMessage;
		this.status = status;
		this.deleted = deleted;
	}
}
