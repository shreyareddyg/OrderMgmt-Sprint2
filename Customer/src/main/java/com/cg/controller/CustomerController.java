package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.Dto.Customer;
import com.cg.Service.CustomerService;
import com.cg.Service.ICustomerService;
import com.cg.util.Result;

@RestController
@RequestMapping("/customer")
@CrossOrigin("http://localhost:4200")
public class CustomerController {

	@Autowired
	ICustomerService icustomerService;
	
	
	
	@GetMapping(path="/users")
	public ResponseEntity<Iterable<Customer>> getUsers() {
		
		Iterable<Customer> users = icustomerService.findAll();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}

	@PostMapping(path="/users")
	public ResponseEntity<Result> postUsers(@RequestBody Customer customer) {
		
		icustomerService.save(customer);
		return new ResponseEntity<>(new Result("Created", "Sucess"),HttpStatus.OK);
	}
}
