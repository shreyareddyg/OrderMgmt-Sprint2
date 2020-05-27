package com.cg.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.Dto.Cart;
import com.cg.Dto.OrderProductMap;
import com.cg.Dto.Orders;
import com.cg.exceptions.ErrorCode;
import com.cg.exceptions.OrderException;
import com.cg.exceptions.OrderProjectException;
import com.cg.repository.CartRepository;
import com.cg.repository.OrderProductMapRepository;
import com.cg.repository.OrderRepository;

@Service
@Transactional
public class OrderService implements IOrderService{
	private static final String ACTION_1 = "Exception while writing data to persistant layer";
	
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderProductMapRepository orderProductMapRepository;

    
    /*******************************************************************************************************
	 * Function Name : createNewOrder 
	 * Input Parameters :  Orders
	 * Return Type : String 
	 * Throws : OrderException, OrderProjectException
	 * Description : to create order
	 ********************************************************************************************************/

    public String createNewOrder(Orders order)  throws OrderException, OrderProjectException{
        if (validateOrder(order)) {
            try {
                String orderId = UUID.randomUUID().toString();
                order.setOrderInitiateTime(new Date());
                order.setOrderId(orderId);
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DATE,3);
                order.setOrderDispatchTime(c.getTime());
                orderRepository.save(order);
                List<Cart> products = cartRepository.findByUserId(order.getUserId());
                for (Cart cart : products) {
                    OrderProductMap map = new OrderProductMap();
                    map.setOrderId(orderId);
                    map.setProductId(cart.getProductId());
                    orderProductMapRepository.save(map);
                    cartRepository.deleteByUserIdAndProductId(order.getUserId(), cart.getProductId());
                }
                return orderId;
            } catch (Exception e) {
                throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, ACTION_1, e);
            }
        }
        throw new OrderException(ErrorCode.BAD_DATA, "Valid date is required");

    }

    
    /*******************************************************************************************************
	 * Function Name : removeOrder 
	 * Input Parameters :  orderId
	 * Return Type : void 
	 * Throws : OrderException, OrderProjectException
	 * Description : to delete order
	 ********************************************************************************************************/
  
    public void removeOrder(String orderId)  throws OrderException, OrderProjectException{
        if (orderId == null || orderId.isEmpty()) {
            throw new OrderException(ErrorCode.BAD_DATA, "Valid Order Id is required");
        }
        try {
            orderRepository.deleteByOrderId(orderId);
            orderProductMapRepository.deleteByOrderId(orderId);
        } catch (Exception e) {
            throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION,ACTION_1, e);
        }
    }

    /*******************************************************************************************************
  	 * Function Name : findOrderByUserId 
  	 * Input Parameters :  userId
  	 * Return Type : Iterable 
  	 * Throws : OrderProjectException
  	 * Description : to show the list of orders according to user
  	 ********************************************************************************************************/
    
    public Iterable<Orders> findOrderByUserId(String userId) throws OrderException, OrderProjectException{
    	 Iterable<Orders> orders = new ArrayList<Orders>();
    	 try {
    		 if (userId == null || userId.isEmpty()) {
    			 orders= orderRepository.findAll();
    	            throw new OrderException(ErrorCode.BAD_DATA, "Valid userId  is required");
    	        }
    		 else
    		 {
    			 orders = orderRepository.findByUserId(userId);
    		 }
             for(Orders order : orders){
                 List<OrderProductMap> products = orderProductMapRepository.getOrderProductMapByOrderId(order.getOrderId());
                 order.setProducts(products);
             }
             return orders;
    	 }
        catch (Exception e) {
            throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, ACTION_1, e);
        }
    }
    
    
    /*******************************************************************************************************
   	 * Function Name : validateOrder 
   	 * Input Parameters :  order
   	 * Return Type : Boolean 
   	 * Throws : OrderException
   	 * Description : to validate order
   	 ********************************************************************************************************/
         private Boolean validateOrder(Orders order) throws OrderException{
        if (order.getAddressId() == null || order.getAddressId().isEmpty()) {
            throw new OrderException(ErrorCode.BAD_DATA, "Valid Address Id is required");
        }
   
        if (order.getUserId() == null || order.getUserId().isEmpty()) {
            throw new OrderException(ErrorCode.BAD_DATA, "Valid User Id is required");
        }
        return true;
    }



}
