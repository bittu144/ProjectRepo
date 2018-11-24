package com.shopware.response;

import lombok.Data;

@Data
public class TempAddressResponse {

	private long id;
	private String state;
	private String city;
	private String country;
	private String street;
	private String houseNo;
	private String addresType;
	private String msisdn;

}
