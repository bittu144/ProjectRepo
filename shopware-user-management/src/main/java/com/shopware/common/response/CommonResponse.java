package com.shopware.common.response;


import java.util.List;

import com.shopware.common.request.OtherInfo;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CommonResponse {
	private CommonResponseStatus responseStatus;
	private Object resObj;
	private List<OtherInfo> otherInfo;

}
