package com.shopware.exception;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shopware.common.response.CommonResponse;
import com.shopware.common.response.CommonResponseStatus;
import com.shopware.utility.ErrorCode;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	@ExceptionHandler(value = { Exception.class })
	public CommonResponse handleException(HttpServletRequest request, Exception ex) {
		ex.printStackTrace();
		CommonResponse commonResponse = new CommonResponse();
		CommonResponseStatus responseStatus = new CommonResponseStatus();
		int code = ErrorCode.INTERNAL_SERVER_ERROR.getStatus();
		String errorMessage = ErrorCode.INTERNAL_SERVER_ERROR.getMessage(messageSource, null, Locale.getDefault());
		responseStatus.setStatusCode(code);
		responseStatus.setStatus(ErrorCode.FAILED.getMessage(messageSource, null, Locale.getDefault()));
		responseStatus.setMessage(errorMessage);
		commonResponse.setResponseStatus(responseStatus);
		commonResponse.setResObj(null);
		return commonResponse;
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	@ExceptionHandler(value = { GenericException.class })
	public CommonResponse genericExceptionHandler(HttpServletRequest request, GenericException ex) {
		CommonResponse commonResponse = new CommonResponse();
		CommonResponseStatus responseStatus = new CommonResponseStatus();
		responseStatus.setStatusCode(ex.getStatusCode());
		responseStatus.setStatus(ex.getStatus());
		responseStatus.setMessage(ex.getMessage());
		commonResponse.setResponseStatus(responseStatus);
		commonResponse.setResObj(null);
		return commonResponse;
	}

}
