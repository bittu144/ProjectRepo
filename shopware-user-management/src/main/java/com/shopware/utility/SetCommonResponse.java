package com.shopware.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.shopware.common.response.CommonResponse;
import com.shopware.common.response.CommonResponseStatus;

@Service
public class SetCommonResponse {

	@Autowired
	private MessageSource messageSource;

	public CommonResponse setCommonResponse(Object obj, String message, int statusCode) {
		CommonResponseStatus responseStatus = new CommonResponseStatus();
		responseStatus.setMessage(message);
		responseStatus.setStatus(ErrorCode.SUCCESS.getMessage(messageSource, null, null));
		responseStatus.setStatusCode(statusCode);
		return new CommonResponse(responseStatus, obj);

	}

}
