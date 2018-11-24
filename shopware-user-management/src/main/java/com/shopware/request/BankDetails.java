package com.shopware.request;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BankDetails {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "ID")
	private String accountId;
	
	@Column(name = "ID")
	private String branchName;
	
	@Column(name = "ID")
	private String branchCode;
	
}
