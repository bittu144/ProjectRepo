package com.shopware.response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "REGISTRATION")
public class LoginResponse {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "MSISDN", nullable = false, length = 20)
	private long msisdn;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "LOGIN_FAIL_COUNT")
	private int loginFailedCount;

	@Column(name = "BLOCKED_TIME", nullable = false, length = 10)
	private String blockedTime;

}
