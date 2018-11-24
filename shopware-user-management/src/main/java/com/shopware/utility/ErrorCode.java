package com.shopware.utility;

import java.util.Locale;

import org.springframework.context.MessageSource;

public enum ErrorCode {

	REGISTRATION_SUCCESSFULL(501), NOTIFICATION_lOCALE(502), SUCCESS(500), INVALID_PASSWORD(503), USER_NOT_REGISTERED(
			504), INTERNAL_SERVER_ERROR(505), FAILED(506), USER_HAS_BLOCKED(507), LOGIN_SUCCESS(508), RECEIVER_BLOCKED(
					509), SENDER_BLOCKED(510), RECEIVER_NOT_ELIGIBLE(511), SENDER_NOT_ELIGIBLE(512), BALACE_INSUFFICIANT(513);
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
