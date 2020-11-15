package com.library.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	
	@Autowired
	UserService userService ;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/emailExists")
	@ResponseBody
	public boolean emailExists(@RequestParam String email) {
		return userService.emailExists(email);
	}
	
	
	
	
	
}
