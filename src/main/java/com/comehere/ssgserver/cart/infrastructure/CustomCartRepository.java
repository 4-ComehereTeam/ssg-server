package com.comehere.ssgserver.cart.infrastructure;

import java.util.List;
import java.util.UUID;

import com.comehere.ssgserver.cart.dto.ItemCountDTO;

public interface CustomCartRepository {

	List<ItemCountDTO> getCartId(UUID uuid);
}
