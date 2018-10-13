package com.shopware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shopware.common.response.CommonResponse;
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

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public CommonResponse test() {
		return userManagementService.test();
	}
}
