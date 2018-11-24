package com.shopware.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shopware.common.response.CommonResponse;
import com.shopware.exception.GenericException;
import com.shopware.request.BalanceTransferRequest;
import com.shopware.service.BalanceManagementService;

@RestController
public class BalanceManagementController {

	private BalanceManagementService balanceManagementService;

	@RequestMapping(value = "/balance_transfer", method = RequestMethod.POST)
	public CommonResponse balanceTransfer(@RequestBody BalanceTransferRequest balanceTransferRequest) throws GenericException {
		return balanceManagementService.balanceTransfer(balanceTransferRequest);
	}

}
