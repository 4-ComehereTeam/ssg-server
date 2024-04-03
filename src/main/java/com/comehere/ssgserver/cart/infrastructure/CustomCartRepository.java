package com.comehere.ssgserver.cart.infrastructure;

import java.util.List;
import java.util.UUID;

import com.comehere.ssgserver.cart.dto.ItemCountDTO;
import com.comehere.ssgserver.cart.dto.request.ChangeStateReqDTO;

public interface CustomCartRepository {

	List<ItemCountDTO> getCartId(UUID uuid);

	Long updateCheckState(UUID uuid, ChangeStateReqDTO changeStateReqDTO);

	Long updatePinState(UUID uuid, ChangeStateReqDTO changeStateReqDTO);
}
