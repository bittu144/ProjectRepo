package com.shopware.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.shopware.Dao.UserManagementDao;
import com.shopware.common.response.CommonResponse;
import com.shopware.exception.GenericException;
import com.shopware.request.FetchProfileRequest;
import com.shopware.request.LoginRequest;
import com.shopware.request.Registration;
import com.shopware.request.WalletDetailsRequest;
import com.shopware.response.BlockedUserDetails;
import com.shopware.response.LoginResponse;
import com.shopware.response.PerAddressResponse;
import com.shopware.response.ProfileResponse;
import com.shopware.response.TempAddressResponse;
import com.shopware.response.UserProfileResponse;
import com.shopware.utility.ErrorCode;
import com.shopware.utility.SetCommonResponse;
import com.shopware.utility.Utility;

@Service
public class UserManagementService {

	@Autowired
	private UserManagementDao userManagementDao;

	@Value("${user.blocked.time}")
	private long defaultUnBlockedTime;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private SetCommonResponse commonResponse;

	public CommonResponse registrationService(Registration registration) {
		registration.getRegistrationRequest().setBlockedTime(null);
		userManagementDao.registrationDao(registration);

		List<WalletDetailsRequest> walletDetailsList = new ArrayList<WalletDetailsRequest>();
		WalletDetailsRequest walletDetailsRequest = new WalletDetailsRequest();
		walletDetailsRequest.setBalance("0");
		walletDetailsRequest.setEligibility("1");
		walletDetailsRequest.setInsertDate(new Date() + "");
		walletDetailsRequest.setMsisdn(registration.getRegistrationRequest().getMsisdn());
		walletDetailsRequest.setStatus("1");
		walletDetailsRequest.setWallet("cash");
		walletDetailsRequest.setWalletId(Long.parseLong(Utility.getRandomValue()));
		walletDetailsRequest.setWalletType("1");
		walletDetailsList.add(walletDetailsRequest);

		WalletDetailsRequest walletDetailsRequest2 = new WalletDetailsRequest();
		walletDetailsRequest2.setBalance("0");
		walletDetailsRequest2.setEligibility("1");
		walletDetailsRequest2.setInsertDate(new Date() + "");
		walletDetailsRequest2.setMsisdn(registration.getRegistrationRequest().getMsisdn());
		walletDetailsRequest2.setStatus("1");
		walletDetailsRequest2.setWallet("cash");
		walletDetailsRequest2.setWalletId(Long.parseLong(Utility.getRandomValue()));
		walletDetailsRequest2.setWalletType("2");
		walletDetailsList.add(walletDetailsRequest2);
		//userManagementDao.walletCreation(walletDetailsList);

		return commonResponse.setCommonResponse(null,

				ErrorCode.REGISTRATION_SUCCESSFULL.getMessage(messageSource, null, null),
				ErrorCode.REGISTRATION_SUCCESSFULL.getStatus());
	}

	public CommonResponse login(LoginRequest loginRequest) throws GenericException {

		BlockedUserDetails blockedUserDetails = new BlockedUserDetails();
		List<LoginResponse> loginResponses = null;
		if (Pattern.matches("^[A-Za-z0-9-_#$]+@[A-Za-z0-9]+(\\.[A-Za-z]+)*(\\.[A-Za-z]{2,})$",
				loginRequest.getUserId())) {
			loginResponses = userManagementDao.login(loginRequest, "email");
		}

		if (Pattern.matches("[0-9]{10,18}", loginRequest.getUserId())) {
			loginResponses = userManagementDao.login(loginRequest, "msisdn");
		}
		if (Pattern.matches("[A-Za-z0-9@#$*_]", loginRequest.getUserId())) {
			loginResponses = userManagementDao.login(loginRequest, "user_Name");
		}

		if (loginResponses == null || loginResponses.isEmpty())
			throw new GenericException(ErrorCode.FAILED.getMessage(messageSource, null, Locale.getDefault()),
					ErrorCode.USER_NOT_REGISTERED.getStatus(),
					ErrorCode.USER_NOT_REGISTERED.getMessage(messageSource, null, Locale.getDefault()), null);

		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		LoginResponse loginResponse = loginResponses.get(0);
		if (!loginResponse.getStatus().equals("5")) {
			if (!loginResponse.getPassword().equals(loginRequest.getPassword())) {

				if (!String.valueOf(loginResponse.getLoginFailedCount()).equals("5")) {
					int updateLoginFailedCount = loginResponse.getLoginFailedCount() + 1;
					userManagementDao.updateLoginFailedCountTime(loginResponse.getMsisdn(), updateLoginFailedCount);
				} else {
					userManagementDao.updateBlockedTime(currentTime, loginResponse.getMsisdn(), 5);
				}
				throw new GenericException(ErrorCode.FAILED.getMessage(messageSource, null, Locale.getDefault()),
						ErrorCode.INVALID_PASSWORD.getStatus(),
						ErrorCode.INVALID_PASSWORD.getMessage(messageSource, null, Locale.getDefault()), null);
			}
		} else {

			long blockedTime = 0;
			try {
				Date date = sdf.parse(loginResponse.getBlockedTime());
				blockedTime = date.getTime();
			} catch (ParseException e) {
			}

			Date currentDateTime = new Date();
			long unBlockedTime = (currentDateTime.getTime() - blockedTime) / 1000;
			long leftUnBlockedTime = defaultUnBlockedTime - unBlockedTime;
			if (unBlockedTime < defaultUnBlockedTime) {
				blockedUserDetails.setBlockedTime(loginResponse.getBlockedTime() + "");
				blockedUserDetails.setUnBlockedTime(leftUnBlockedTime + "");
				String unblockTime = "";
				if (leftUnBlockedTime > 60) {
					long min = leftUnBlockedTime / 60;
					long sec = leftUnBlockedTime % 60;
					unblockTime = unblockTime + min + " minute " + sec + " seconds";
				} else {
					unblockTime = leftUnBlockedTime + " seconds";

				}
				throw new GenericException(ErrorCode.FAILED.getMessage(messageSource, null, Locale.getDefault()),
						ErrorCode.USER_HAS_BLOCKED.getStatus(), ErrorCode.USER_HAS_BLOCKED.getMessage(messageSource,
								new Object[] { unblockTime }, Locale.getDefault()),
						blockedUserDetails);
			}

		}
		userManagementDao.updateLoginFailedCountTime(loginResponse.getMsisdn(), 0);
		return commonResponse.setCommonResponse(null, ErrorCode.LOGIN_SUCCESS.getMessage(messageSource, null, null),
				ErrorCode.LOGIN_SUCCESS.getStatus());

	}

	public CommonResponse profile(FetchProfileRequest profileRequest) {
		return commonResponse.setCommonResponse(null,
				ErrorCode.REGISTRATION_SUCCESSFULL.getMessage(messageSource, null, null),
				ErrorCode.REGISTRATION_SUCCESSFULL.getStatus());
	}

	public List<UserProfileResponse> getAddressData(FetchProfileRequest profileRequest) {

		List<UserProfileResponse> profileResponses = new ArrayList<UserProfileResponse>();

		List<PerAddressResponse> addressResponses = userManagementDao.getAddress(profileRequest.getUserId());
		List<ProfileResponse> registrationResponse = userManagementDao.getProfile(profileRequest);

		Map<String, List<PerAddressResponse>> map = new HashMap<String, List<PerAddressResponse>>();

		for (PerAddressResponse addressBean : addressResponses) {

			if (map.containsKey(addressBean.getMsisdn())) {

				List<PerAddressResponse> responses = map.get(addressBean.getMsisdn());
				responses.add(new PerAddressResponse(addressBean.getId(), addressBean.getState(), addressBean.getCity(),
						addressBean.getCountry(), addressBean.getStreet(), addressBean.getHouseNo(),
						addressBean.getAddresType(), addressBean.getMsisdn()));

				map.put(addressBean.getMsisdn(), responses);
			} else {

				List<PerAddressResponse> responses = new ArrayList<PerAddressResponse>();
				responses.add(new PerAddressResponse(addressBean.getId(), addressBean.getState(), addressBean.getCity(),
						addressBean.getCountry(), addressBean.getStreet(), addressBean.getHouseNo(),
						addressBean.getAddresType(), addressBean.getMsisdn()));

				map.put(addressBean.getMsisdn(), responses);

			}

		}

		for (int i = 0; i < registrationResponse.size(); i++) {
			UserProfileResponse userProfileResponse = new UserProfileResponse();

			List<PerAddressResponse> perAddressResponses = map.get(registrationResponse.get(i).getMsisdn() + "");

			PerAddressResponse perAddressResponse = new PerAddressResponse();
			TempAddressResponse tempAddressResponse = new TempAddressResponse();
			for (int j = 0; j < perAddressResponses.size(); j++) {
				PerAddressResponse perAddress = perAddressResponses.get(j);
				if (perAddress.getAddresType().equals("0")) {
					perAddressResponse.setAddresType(perAddress.getAddresType());
					perAddressResponse.setCity(perAddress.getCity());
					perAddressResponse.setCountry(perAddress.getCountry());
					perAddressResponse.setHouseNo(perAddress.getHouseNo());
					perAddressResponse.setId(perAddress.getId());
					perAddressResponse.setMsisdn(perAddress.getMsisdn());
					perAddressResponse.setState(perAddress.getState());
					perAddressResponse.setStreet(perAddress.getStreet());

				}

				if (perAddress.getAddresType().equals("1")) {
					tempAddressResponse.setAddresType(perAddress.getAddresType());
					tempAddressResponse.setCity(perAddress.getCity());
					tempAddressResponse.setCountry(perAddress.getCountry());
					tempAddressResponse.setHouseNo(perAddress.getHouseNo());
					tempAddressResponse.setId(perAddress.getId());
					tempAddressResponse.setMsisdn(perAddress.getMsisdn());
					tempAddressResponse.setState(perAddress.getState());
					tempAddressResponse.setStreet(perAddress.getStreet());

				}

			}

			ProfileResponse registrationRequest = registrationResponse.get(i);
			userProfileResponse.setAge(registrationRequest.getAge());
			userProfileResponse.setAltEmail(registrationRequest.getAltEmail());
			userProfileResponse.setAltMsisdn(registrationRequest.getAltMsisdn());
			userProfileResponse.setDob(registrationRequest.getDob());
			userProfileResponse.setEmail(registrationRequest.getEmail());
			userProfileResponse.setFatherName(registrationRequest.getFatherName());
			userProfileResponse.setId(registrationRequest.getId());
			userProfileResponse.setMotherName(registrationRequest.getMotherName());
			userProfileResponse.setUserName(registrationRequest.getUserName());
			userProfileResponse.setRefCode(registrationRequest.getRefCode());
			userProfileResponse.setMpin(registrationRequest.getMpin());
			userProfileResponse.setMsisdn(registrationRequest.getMsisdn());
			userProfileResponse.setPassword(registrationRequest.getPassword());
			userProfileResponse.setPerAddressResponse(perAddressResponse);
			userProfileResponse.setTempAddressResponse(tempAddressResponse);

			profileResponses.add(userProfileResponse);

		}

		return profileResponses;

	}

}
