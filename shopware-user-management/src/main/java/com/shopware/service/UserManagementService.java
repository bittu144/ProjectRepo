package com.shopware.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopware.Dao.UserManagementDao;
import com.shopware.common.response.CommonResponse;
import com.shopware.request.Registration;
import com.shopware.utility.SetCommonResponse;

@Service
public class UserManagementService {

	@Autowired
	private UserManagementDao userManagementDao;

	@Autowired
	private SetCommonResponse commonResponse;

	public CommonResponse registrationService(Registration registration) {
		userManagementDao.registrationDao(registration);
		return commonResponse.setCommonResponse(null, registration.getRegistrationRequest().getInfo());
	}

	public CommonResponse test() {
		return commonResponse.setCommonResponse(null, null);
	}

}
