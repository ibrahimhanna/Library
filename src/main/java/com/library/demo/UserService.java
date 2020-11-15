package com.library.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	
	
	public List<User> fetchPersons(){
		
		return userRepository.findAll();
	}
	
	
	
	public boolean saveUser(User user) {
		
		userRepository.save(user);
		
		return true;
	}
	
	
	
	public boolean emailExists(String email) {
		
		List<User> user = userRepository.findByEmail(email);
		if(user.size()>0) 
			return true;
		else
			return false;
		
	}
	
	
	
}
