package com.shopware.utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.shopware.common.request.OtherInfo;
import com.shopware.common.response.CommonResponse;
import com.shopware.common.response.CommonResponseStatus;

@Service
public class SetCommonResponse {

	@Autowired
	private MessageSource messageSource;

	public CommonResponse setCommonResponse(Object obj, List<OtherInfo> optionalnfo) {
		CommonResponseStatus responseStatus = new CommonResponseStatus();
		responseStatus.setMessage(ErrorCode.REGISTRATION_SUCCESSFULL.getMessage(messageSource, null, null));
		responseStatus.setStatus(ErrorCode.SUCCESS.getMessage(messageSource, null, null));
		responseStatus.setStatusCode(ErrorCode.REGISTRATION_SUCCESSFULL.getStatus());
		return new CommonResponse(responseStatus, obj, optionalnfo);

	}

}
