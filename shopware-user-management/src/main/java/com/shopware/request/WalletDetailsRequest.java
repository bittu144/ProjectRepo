package com.shopware.request;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "WALLET_DETAILS")
public class WalletDetailsRequest {

	@Id
	@Column(name = "WALLET_ID", length = 50)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "WALLETS", length = 50)
	private String wallet;

	@Column(name = "WALLET_TYPE")
	private String walletType;

	@Column(name = "WALLET_UNIQUE_ID", unique = true, length = 50)
	private long walletId;

	@Column(name = "BALANCE", length = 100)
	private String balance;

	@Column(name = "INSERT_DATE", length = 50)
	private String insertDate;

	@Column(name = "STATUS", length = 20)
	private String status;

	@Column(name = "ELIGIBILITY", length = 20)
	private String eligibility;

	@Column(name = "MSISDN", length = 100)
	private long msisdn;

}
