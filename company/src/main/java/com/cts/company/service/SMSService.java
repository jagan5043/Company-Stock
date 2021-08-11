package com.cts.company.service;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SMSService {

	private final static String ACCOUNT_SID = "AC26ee523b2b0bef18a4dae5b52687ed76";
	private final static String AUTH_ID = "a205bcfc8346f5ae1d737fa60c49b82c";

	static {
		Twilio.init(ACCOUNT_SID, AUTH_ID);
	}

	public boolean send2FaCode(String mobilenumber, String twoFaCode) {

		Message.creator(new PhoneNumber(mobilenumber), new PhoneNumber("+13107735086"),
				"Your Two Factor Authentication code is: " + twoFaCode).create();

		return true;

	}
}