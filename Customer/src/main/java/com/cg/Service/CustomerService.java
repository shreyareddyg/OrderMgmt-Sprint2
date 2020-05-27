package com.cg.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.Dto.Customer;
import com.cg.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService implements ICustomerService{

	@Autowired
	CustomerRepository customerRepository;

	public Iterable<Customer> findAll() {
	
		return customerRepository.findAll();
	}

	public Customer save(Customer customer) {
		
		return customerRepository.save(customer);
	}
	
	
}
