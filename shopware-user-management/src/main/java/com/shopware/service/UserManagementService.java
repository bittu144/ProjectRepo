package com.shopware.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopware.Dao.UserManagementDao;
import com.shopware.common.response.CommonResponse;
import com.shopware.request.FetchProfileRequest;
import com.shopware.request.Registration;
import com.shopware.response.PerAddressResponse;
import com.shopware.response.ProfileResponse;
import com.shopware.response.TempAddressResponse;
import com.shopware.response.UserProfileResponse;
import com.shopware.utility.SetCommonResponse;

@Service
public class UserManagementService {

	@Autowired
	private UserManagementDao userManagementDao;

	@Autowired
	private SetCommonResponse commonResponse;

	public CommonResponse registrationService(Registration registration) {
		userManagementDao.registrationDao(registration);
		return commonResponse.setCommonResponse(null);
	}

	public CommonResponse test() {
		return commonResponse.setCommonResponse(null);
	}

	public CommonResponse profile(FetchProfileRequest profileRequest) {
		return commonResponse.setCommonResponse(getAddressData(profileRequest));
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
