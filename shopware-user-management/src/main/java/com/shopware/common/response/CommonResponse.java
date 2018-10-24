package com.shopware.common.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
	private CommonResponseStatus responseStatus;
	private Object resObj;
//	private List<OtherInfo> otherInfo;

}
