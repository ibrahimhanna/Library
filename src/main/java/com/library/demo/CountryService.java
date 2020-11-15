package com.library.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

	@Autowired
	CountryRepository countryRepository;
	
	
	public List<Country> getCountries(){
		
		return countryRepository.findAll();
	}
	
	
}
