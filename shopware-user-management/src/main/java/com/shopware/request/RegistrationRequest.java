package com.shopware.request;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.shopware.common.request.OtherInfo;
import com.shopware.common.request.SourceInfoRequest;
import com.shopware.common.request.TransactionInfoRequest;

import lombok.Data;

@Data
@Entity
@Table(name = "REGISTRATION")
public class RegistrationRequest {

	@Id
	@Column(name = "REGISTRATION_ID", nullable = false, length = 20)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "USER_NAME", unique = true, nullable = false, length = 50)
	private String userName;

	@Column(name = "MSISDN", nullable = false, length = 20)
	private long msisdn;

	@Column(name = "ALT_MSISDN", length = 20)
	private String altMsisdn;

	@Column(name = "EMAIL", unique = true, length = 50)
	private String email;

	@Column(name = "ALT_EMAIL", length = 50)
	private String altEmail;

	@Column(name = "PASSWORD", length = 20)
	private String password;

	@Column(name = "DOB", length = 20)
	private String dob;

	@Column(name = "MOTHER_NAME", length = 20)
	private String motherName;

	@Column(name = "FATHER_NAME", length = 20)
	private String fatherName;

	@Column(name = "AGE", length = 20)
	private String age;

	@Column(name = "REF_CODE", length = 20)
	private String refCode;

	@Column(name = "MPIN", nullable = false, length = 10)
	private String mpin;

	@Column(name = "STATUS", nullable = false, length = 10)
	private int status;

	@Column(name = "BLOCKED_TIME", length = 50)
	private String blockedTime;

	@Column(name = "LOGIN_FAIL_COUNT", nullable = false, length = 10)
	private int loginFailCount;

	@OneToOne(mappedBy = "perRequest", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private PermanentAddressRequest permanentAddressRequest;

	@OneToOne(mappedBy = "tempRequest", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private TempAddressRequest tempAddressRequest;

	@OneToOne(mappedBy = "sourceInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private SourceInfoRequest sourceInfoRequest;

	@OneToOne(mappedBy = "transactionInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private TransactionInfoRequest txnInfo;
	
	@OneToMany(mappedBy = "other", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<OtherInfo> otherInfo;

}
