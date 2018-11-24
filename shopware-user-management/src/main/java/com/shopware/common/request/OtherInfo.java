package com.shopware.common.request;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.shopware.request.RegistrationRequest;

import lombok.Data;

@Data
@Entity
@Table(name = "OTHER_INFO")
public class OtherInfo {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "KEY")
	private String key;

	@Column(name = "VALUE")
	private String value;

	@ManyToOne
	@JoinColumn(name = "REGISTRATION_ID", unique = true, nullable = false)
	private RegistrationRequest other;

}
