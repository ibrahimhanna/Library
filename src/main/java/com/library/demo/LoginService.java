package com.library.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	
	@Autowired
	LoginRepository loginRepository;
	
	
	
	
	public boolean login(String username, String password) {
		
		List<Login> user = loginRepository.findByUsername(username);
		if(user!=null && user.get(0).getPassword().equals(password)) {
			return true;
		}
		
		
		return false;
	}
	
	
	public boolean saveLogin(Login login) {
		
		loginRepository.save(login);
		return true;
	}
	
	
	
}
