package com.cg.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.Dto.CartDTO;
import com.cg.Dto.OrderProductMap;
import com.cg.Dto.OrdersDTO;
import com.cg.Service.OrderService;
import com.cg.util.Result;

@RestController
@RequestMapping("/order")
@CrossOrigin("http://localhost:4200")
public class OrderController {

	
	@Autowired
	private OrderService orderService;
	
	
	
	@DeleteMapping(path="/{orderId}")
	public ResponseEntity<Void> removeOrder(@PathVariable String orderId) {
		orderService.removeOrder(orderId);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@PostMapping(path="/createOrder")
	public ResponseEntity<Result> addNewOrder (@RequestBody OrdersDTO order) {
		String orderId = orderService.createNewOrder(order);
		return new ResponseEntity<>(new Result(orderId, "Created"), HttpStatus.OK);
	}

	

	@GetMapping(path="/getorder/{userId}")
	public ResponseEntity<Iterable<OrdersDTO>> getOrders(@PathVariable String userId) {
		 Iterable<OrdersDTO> orders = orderService.findOrderByUserId(userId);
		 return new ResponseEntity<>(orders,HttpStatus.OK);
	}
}
