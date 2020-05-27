package com.cg.service;

import java.util.List;

import com.cg.Dto.Cart;
import com.cg.exceptions.CartException;
import com.cg.exceptions.OrderProjectException;

public interface ICartService {

	
	void addProductToCart(Cart cart) throws CartException, OrderProjectException;
	
	List<Cart> findCartByUserId(String userId) throws CartException, OrderProjectException;
	
	void deleteProductFromCart(String userId, String productId) throws CartException, OrderProjectException;
	
	void updateCart(Cart cart) throws CartException, OrderProjectException;
	

}
