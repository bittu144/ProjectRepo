package com.shopware.common.response;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CommonResponse {
	private CommonResponseStatus responseStatus;
	private Object resObj;
//	private List<OtherInfo> otherInfo;

}
