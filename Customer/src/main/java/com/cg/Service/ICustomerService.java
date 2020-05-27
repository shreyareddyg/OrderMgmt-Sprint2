package com.cg.Service;

import com.cg.Dto.Customer;

public interface ICustomerService {

	
	Iterable<Customer> findAll();
	
	Customer save(Customer customer);
}
