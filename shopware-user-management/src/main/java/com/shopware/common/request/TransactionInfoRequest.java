package com.shopware.common.request;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.shopware.request.RegistrationRequest;

import lombok.Data;

@Data
@Entity
@Table(name = "TRANSACTION_INFO")
public class TransactionInfoRequest {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "SERVICE_TYPE")
	private String serviceType;

	@Column(name = "REQUEST_ID")
	private String requestId;

	@Column(name = "RESPONSE_ID")
	private String responseId;

	@OneToOne
	@JoinColumn(name = "REGISTRATION_ID", unique = true, nullable = false)
	private RegistrationRequest transactionInfo;

}
