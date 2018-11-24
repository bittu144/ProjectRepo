package com.shopware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shopware.common.response.CommonResponse;
import com.shopware.exception.GenericException;
import com.shopware.request.FetchProfileRequest;
import com.shopware.request.LoginRequest;
import com.shopware.request.Registration;
import com.shopware.service.UserManagementService;

@RestController
@RequestMapping(value = "/user")
public class UserRegistrationController {

	@Autowired
	private UserManagementService userManagementService;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public CommonResponse registration(@RequestBody Registration registration) {
		return userManagementService.registrationService(registration);
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public CommonResponse profile(@RequestBody FetchProfileRequest profileRequest) {
		return userManagementService.profile(profileRequest);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public CommonResponse login(@RequestBody LoginRequest loginRequest) throws GenericException {
		return userManagementService.login(loginRequest);
	}

}
