package com.user.microservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@GetMapping("status/user")
 public String userStatus() {
	 return "Hello Welocome ! This is user Status method :P";
 }
	
}
