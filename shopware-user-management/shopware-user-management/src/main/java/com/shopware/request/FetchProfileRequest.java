package com.shopware.request;

import lombok.Data;

@Data
public class FetchProfileRequest {

	private String userId;
	private int limit;
	private int offset;

}
