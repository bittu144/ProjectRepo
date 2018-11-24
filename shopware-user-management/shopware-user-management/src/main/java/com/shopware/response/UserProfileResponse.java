package com.shopware.response;

import lombok.Data;

@Data
public class UserProfileResponse {

	private long id;
	private String userName;
	private long msisdn;
	private String altMsisdn;
	private String email;
	private String altEmail;
	private String password;
	private String dob;
	private String motherName;
	private String fatherName;
	private String age;
	private String refCode;
	private String mpin;
	private PerAddressResponse perAddressResponse;
	private TempAddressResponse tempAddressResponse;

}
