package com.comehere.ssgserver.purchase.infrastructure;

import java.util.UUID;

public interface CustomAddressRepository {

	Long getDefaultAddress(UUID uuid);
}
