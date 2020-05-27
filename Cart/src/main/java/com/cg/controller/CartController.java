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

import com.cg.Dto.Cart;
import com.cg.service.CartService;
import com.cg.service.ICartService;
import com.cg.util.Result;


@RestController
@RequestMapping("/cart")
@CrossOrigin("http://localhost:4200")
public class CartController {

	@Autowired
	private ICartService icartService;

	//The parameters Cart(userId, productId, quantity) are
	//passed to the method and is added to the cart.
	@PostMapping(path="/addtocart")
	public ResponseEntity<Result> addItemToCart(@RequestBody Cart cart) {
		icartService.addProductToCart(cart);
		return new ResponseEntity<>(new Result("Added To Cart", "Completed"), HttpStatus.OK);
	}

	// It is for getting the list of products added to cart
	@GetMapping(path="/products/{userId}")
	public ResponseEntity<List<Cart>> getProductsFromCart(@PathVariable String userId) {
		List<Cart> cart = icartService.findCartByUserId(userId);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}

	//Update the cart by changing it's values
	@PutMapping(path="/addtocart")
	public ResponseEntity<Result> updateProductsFromCart(@RequestBody Cart cart) {
		icartService.updateCart(cart);
		return new ResponseEntity<>(new Result("Cart Updated", "Completed"),HttpStatus.OK);
	}

	//Delete the product from the cart according to the user
	@DeleteMapping(path="/{userId}/{productId}")
	public ResponseEntity<Void> removeProductFromCart(@PathVariable String userId, @PathVariable String productId) {
		icartService.deleteProductFromCart(userId,productId);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	

	
}
