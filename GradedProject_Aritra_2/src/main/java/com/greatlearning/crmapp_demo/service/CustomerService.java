package com.greatlearning.crmapp_demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.crmapp_demo.entity.Customer;

@Service
public interface CustomerService {

	public List<Customer> findAll();
		
	public Customer findByEmail(String email);
	
	public void save(Customer customer); // save or update
	
	public void deleteByEmail(String email);
}

