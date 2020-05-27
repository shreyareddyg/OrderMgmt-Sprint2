package com.cg.Service;

import com.cg.Dto.Orders;
import com.cg.exceptions.OrderException;
import com.cg.exceptions.OrderProjectException;

public interface IOrderService {

	String createNewOrder(Orders order) throws OrderException, OrderProjectException;
	
	void removeOrder(String orderId) throws OrderException, OrderProjectException;
	
	Iterable<Orders> findOrderByUserId(String userId) throws OrderProjectException;
}
