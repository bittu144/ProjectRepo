package com.shopware.common.request;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.shopware.request.RegistrationRequest;

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MSISDN")
	private RegistrationRequest other;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setOtherInfo(RegistrationRequest otherInfo) {
		this.other = otherInfo;
	}

	@Override
	public String toString() {
		return "OtherInfo [id=" + id + ", key=" + key + ", value=" + value + "]";
	}

}
