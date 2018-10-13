package com.shopware.request;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ADDRESS")
public class PermanentAddressRequest {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "STATE")
	private String state;
	@Column(name = "CITY")
	private String city;
	@Column(name = "COUNTRY")
	private String country;
	@Column(name = "STREET")
	private String street;
	@Column(name = "HOUSE_NO")
	private String houseNo;
	
	@Column(name = "ADDRESS_TYPE")
	private String addresType;

	@OneToOne
	@JoinColumn(name = "MSISDN")
	private RegistrationRequest perRequest;

}
