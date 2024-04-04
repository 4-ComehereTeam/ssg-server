package com.comehere.ssgserver.purchase.vo.resp;

import java.util.List;

import com.comehere.ssgserver.purchase.vo.AddressDetailVO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddressListRespVO {

	List<AddressDetailVO> addressIds;
}
