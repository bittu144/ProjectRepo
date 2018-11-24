package com.shopware.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.shopware.Dao.BalanceManagementDao;
import com.shopware.common.response.CommonResponse;
import com.shopware.exception.GenericException;
import com.shopware.request.BalanceTransferRequest;
import com.shopware.request.WalletDetailsRequest;
import com.shopware.utility.ErrorCode;

@Service
public class BalanceManagementService {

	@Autowired
	private BalanceManagementDao managementDao;

	@Autowired
	private MessageSource messageSource;

	public CommonResponse balanceTransfer(BalanceTransferRequest balanceTransferRequest) throws GenericException {
		List<WalletDetailsRequest> fromWalletDetailsRes = managementDao
				.userWalletDetails(balanceTransferRequest.getFromMsisdn(), balanceTransferRequest.getFromWalletType());

		List<WalletDetailsRequest> toWalletDetailsRes = managementDao
				.userWalletDetails(balanceTransferRequest.getToMsisdn(), balanceTransferRequest.getFromWalletType());

		List<WalletDetailsRequest> systemWalletDetailsRes = managementDao.userWalletDetails(0, "1");

		WalletDetailsRequest fromWalletDetails = fromWalletDetailsRes.get(0);
		WalletDetailsRequest toWalletDetails = toWalletDetailsRes.get(0);
		WalletDetailsRequest systemWalletDetails = systemWalletDetailsRes.get(0);
		if (fromWalletDetails.getStatus().equals("1")) {
			if (fromWalletDetails.getEligibility().equals("1")) {
				if (toWalletDetails.getStatus().equals("1")) {
					if (toWalletDetails.getEligibility().equals("1")) {

						BigDecimal balance = new BigDecimal(fromWalletDetails.getBalance());
						BigDecimal amount = new BigDecimal(balanceTransferRequest.getAmount());
						int compareValue = balance.compareTo(amount);
						if (compareValue < 0)
							throw new GenericException(
									ErrorCode.FAILED.getMessage(messageSource, null, Locale.getDefault()),
									ErrorCode.BALACE_INSUFFICIANT.getStatus(),
									ErrorCode.BALACE_INSUFFICIANT.getMessage(messageSource,
											new Object[] { fromWalletDetails.getBalance() }, Locale.getDefault()),
									null);

						BigDecimal bigDecimal = new BigDecimal(systemWalletDetails.getBalance());
						BigDecimal updateBalToSystemWallet = bigDecimal.add(amount);
						BigDecimal userNewBalance = balance.subtract(amount);
						managementDao.updateBalance(userNewBalance, fromWalletDetails.getWalletId());
						managementDao.updateBalance(updateBalToSystemWallet, systemWalletDetails.getWalletId());
						List<WalletDetailsRequest> updatedSystemWalletDetailsRes = managementDao.userWalletDetails(0,
								"1");
						WalletDetailsRequest updatedSystemWalletDetails = updatedSystemWalletDetailsRes.get(0);
						BigDecimal updatedSysBalance = new BigDecimal(updatedSystemWalletDetails.getBalance());
						int compareUpdatedBalValue = updatedSysBalance.compareTo(amount);
						if (compareUpdatedBalValue < 0)
							throw new GenericException(
									ErrorCode.FAILED.getMessage(messageSource, null, Locale.getDefault()),
									ErrorCode.BALACE_INSUFFICIANT.getStatus(),
									ErrorCode.BALACE_INSUFFICIANT.getMessage(messageSource,
											new Object[] { fromWalletDetails.getBalance() }, Locale.getDefault()),
									null);

						BigDecimal transferBalance = updatedSysBalance.subtract(amount);
						managementDao.updateBalance(transferBalance, systemWalletDetails.getWalletId());
						managementDao.updateBalance(amount, toWalletDetails.getWalletId());

					} else {
						throw new GenericException(
								ErrorCode.FAILED.getMessage(messageSource, null, Locale.getDefault()),
								ErrorCode.RECEIVER_NOT_ELIGIBLE.getStatus(),
								ErrorCode.RECEIVER_NOT_ELIGIBLE.getMessage(messageSource, null, Locale.getDefault()),
								null);
					}

				} else {

					throw new GenericException(ErrorCode.FAILED.getMessage(messageSource, null, Locale.getDefault()),
							ErrorCode.RECEIVER_BLOCKED.getStatus(),
							ErrorCode.RECEIVER_BLOCKED.getMessage(messageSource, null, Locale.getDefault()), null);

				}

			} else {
				throw new GenericException(ErrorCode.FAILED.getMessage(messageSource, null, Locale.getDefault()),
						ErrorCode.SENDER_NOT_ELIGIBLE.getStatus(),
						ErrorCode.SENDER_NOT_ELIGIBLE.getMessage(messageSource, null, Locale.getDefault()), null);

			}

		} else {

			throw new GenericException(ErrorCode.FAILED.getMessage(messageSource, null, Locale.getDefault()),
					ErrorCode.SENDER_BLOCKED.getStatus(),
					ErrorCode.SENDER_BLOCKED.getMessage(messageSource, null, Locale.getDefault()), null);

		}

		return null;
	}

}
