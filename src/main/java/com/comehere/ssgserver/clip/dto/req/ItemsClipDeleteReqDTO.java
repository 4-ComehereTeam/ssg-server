package com.comehere.ssgserver.clip.dto.req;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemsClipDeleteReqDTO {
	private List<Long> itemIds = new ArrayList<>();
}
