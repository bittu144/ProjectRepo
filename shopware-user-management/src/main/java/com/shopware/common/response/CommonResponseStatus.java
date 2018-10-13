package com.shopware.common.response;

import lombok.Data;

@Data
public class CommonResponseStatus {
	private String status;
	private int statusCode;
	private String message;

}
