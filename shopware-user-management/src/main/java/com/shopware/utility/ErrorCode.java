package com.shopware.utility;

import java.util.Locale;

import org.springframework.context.MessageSource;

public enum ErrorCode {

	REGISTRATION_SUCCESSFULL(501), NOTIFICATION_lOCALE(502), SUCCESS(500);
	private int status;

	private ErrorCode(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage(MessageSource messageSource, Object[] arg1, Locale arg2) {
		return messageSource.getMessage(String.valueOf(status), arg1, arg2);
	}

}
