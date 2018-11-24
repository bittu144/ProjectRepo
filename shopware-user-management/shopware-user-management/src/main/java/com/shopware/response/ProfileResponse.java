package com.shopware.response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REGISTRATION")
public class ProfileResponse {

	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "USER_NAME", nullable = false, length = 20)
	private String userName;

	@Id
	@Column(name = "MSISDN", nullable = false, length = 20)
	private long msisdn;

	@Column(name = "ALT_MSISDN", length = 20)
	private String altMsisdn;

	@Column(name = "EMAIL", length = 20)
	private String email;

	@Column(name = "ALT_EMAIL", length = 20)
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(long msisdn) {
		this.msisdn = msisdn;
	}

	public String getAltMsisdn() {
		return altMsisdn;
	}

	public void setAltMsisdn(String altMsisdn) {
		this.altMsisdn = altMsisdn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAltEmail() {
		return altEmail;
	}

	public void setAltEmail(String altEmail) {
		this.altEmail = altEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public String getMpin() {
		return mpin;
	}

	public void setMpin(String mpin) {
		this.mpin = mpin;
	}

	@Override
	public String toString() {
		return "RegistrationRequest [id=" + id + ", userName=" + userName + ", msisdn=" + msisdn + ", altMsisdn="
				+ altMsisdn + ", email=" + email + ", altEmail=" + altEmail + ", password=" + password + ", dob=" + dob
				+ ", motherName=" + motherName + ", fatherName=" + fatherName + ", age=" + age + ", refCode=" + refCode
				+ ", mpin=" + mpin + "]";
	}

}
