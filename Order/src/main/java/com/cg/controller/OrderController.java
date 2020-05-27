package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.Dto.Orders;
import com.cg.Service.IOrderService;
import com.cg.util.Result;

@RestController
@RequestMapping("/order")
@CrossOrigin("http://localhost:4200")
public class OrderController {

	
	@Autowired
	private IOrderService iorderService;
	
	
	//The order gets deleted by providing its id
	
	@DeleteMapping(path="/{orderId}")
	public ResponseEntity<Void> removeOrder(@PathVariable String orderId) {
		iorderService.removeOrder(orderId);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	
	// creating order by passing orders parameter
	@PostMapping(path="/createOrder")
	public ResponseEntity<Result> addNewOrder (@RequestBody Orders order) {
		String orderId = iorderService.createNewOrder(order);
		return new ResponseEntity<>(new Result(orderId, "Created"), HttpStatus.OK);
	}

	
//the details of the order are fetched according to the userid
	@GetMapping(path="/getorder/{userId}")
	public ResponseEntity<Iterable<Orders>> getOrders(@PathVariable String userId) {
		 Iterable<Orders> orders = iorderService.findOrderByUserId(userId);
		 return new ResponseEntity<>(orders,HttpStatus.OK);
	}
}
