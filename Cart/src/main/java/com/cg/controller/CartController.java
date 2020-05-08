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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.Dto.CartDTO;
import com.cg.Service.CartService;
import com.cg.util.Result;


@RestController
@RequestMapping("/cart")
@CrossOrigin("http://localhost:4200")
public class CartController {

	@Autowired
	private CartService cartService;

	
	@PostMapping(path="/addtocart")
	public ResponseEntity<Result> addItemToCart(@RequestBody CartDTO cart) {
		cartService.addProductToCart(cart);
		return new ResponseEntity<>(new Result("Added To Cart", "Completed"), HttpStatus.OK);
	}

	@GetMapping(path="/products/{userId}")
	public ResponseEntity<List<CartDTO>> getProductsFromCart(@PathVariable String userId) {
		List<CartDTO> cart = cartService.findCartByUserId(userId);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}

	@PutMapping(path="/addtocart")
	public ResponseEntity<Result> updateProductsFromCart(@RequestBody CartDTO cart) {
		cartService.updateCart(cart);
		return new ResponseEntity<>(new Result("Cart Updated", "Completed"),HttpStatus.OK);
	}

	@DeleteMapping(path="/{userId}/{productId}")
	public ResponseEntity<Void> removeProductFromCart(@PathVariable String userId, @PathVariable String productId) {
		cartService.deleteProductFromCart(userId,productId);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
}
