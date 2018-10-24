package com.shopware.response;

import lombok.Data;

@Data
public class BlockedUserDetails {
	private String blockedTime;
	private String unBlockedTime;

}
