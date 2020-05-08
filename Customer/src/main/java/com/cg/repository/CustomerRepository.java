package com.cg.repository;

import org.springframework.data.repository.CrudRepository;

import com.cg.Dto.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
}
