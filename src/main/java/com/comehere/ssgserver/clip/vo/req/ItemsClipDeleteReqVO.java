package com.comehere.ssgserver.clip.vo.req;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ItemsClipDeleteReqVO {
	List<Long> itemIds = new ArrayList<>();
}
