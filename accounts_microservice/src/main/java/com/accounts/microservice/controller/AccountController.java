package com.accounts.microservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@GetMapping("status/account")
	public String accountStatus() {
		return "Hello ! Welcome to Accounts status ";
	}
	

}
