package com.shopware.request;

import com.shopware.common.request.SourceInfoRequest;
import com.shopware.common.request.TransactionInfoRequest;

import lombok.Data;

@Data
public class BalanceTransferRequest {

	private String amount;
	private long fromMsisdn;
	private long toMsisdn;
	private String fromWalletType;
	private String toWalletType;
	private String fromWalletId;
	private String toWalletId;
	private String commission;
	private String fee;
	private String promoCode;

	private SourceInfoRequest infoRequest;

	private TransactionInfoRequest transactionInfoRequest;

}
