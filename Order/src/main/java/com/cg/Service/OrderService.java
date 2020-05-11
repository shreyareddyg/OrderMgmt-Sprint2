package com.cg.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.Dto.CartDTO;
import com.cg.Dto.OrderProductMap;
import com.cg.Dto.OrdersDTO;
import com.cg.exceptions.ErrorCode;
import com.cg.exceptions.OrderException;
import com.cg.exceptions.OrderProjectException;
import com.cg.repository.CartRepository;
import com.cg.repository.OrderProductMapRepository;
import com.cg.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderProductMapRepository orderProductMapRepository;

    public String createNewOrder(OrdersDTO order) throws OrderException, OrderProjectException {
        if (validateOrder(order)) {
            try {
                String orderId = UUID.randomUUID().toString();
                order.setOrderInitiateTime(new Date());
                order.setOrderId(orderId);
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DATE,3);
                order.setOrderDispatchTime(c.getTime());
                orderRepository.save(order);
                List<CartDTO> products = cartRepository.findByUserId(order.getUserId());
                for (CartDTO cart : products) {
                    OrderProductMap map = new OrderProductMap();
                    map.setOrderId(orderId);
                    map.setProductStatus(0);
                    map.setProductId(cart.getProductId());
                    map.setGiftStatus(0);
                    map.setProductUIN("somethng");
                    orderProductMapRepository.save(map);
                    cartRepository.deleteByUserIdAndProductId(order.getUserId(), cart.getProductId());
                }
                return orderId;
            } catch (Exception e) {
                throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, "Exception while writing data to persistant layer", e);
            }
        }
        throw new OrderException(ErrorCode.BAD_DATA, "Valid date is required");

    }

    
    public void removeOrder(String orderId) throws OrderException, OrderProjectException {
        if (orderId == null || orderId.isEmpty()) {
            throw new OrderException(ErrorCode.BAD_DATA, "Valid Order Id is required");
        }
        try {
            orderRepository.deleteByOrderId(orderId);
            orderProductMapRepository.deleteByOrderId(orderId);
        } catch (Exception e) {
            throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, "Exception while writing data to persistant layer", e);
        }
    }



    public Iterable<OrdersDTO> findOrderByUserId(String userId) throws OrderProjectException {
    	 Iterable<OrdersDTO> orders = new ArrayList<OrdersDTO>();
    	 try {
    		 if (userId == null || userId.isEmpty()) {
    			 orders= orderRepository.findAll();
    	           // throw new OrderException(ErrorCode.BAD_DATA, "Valid userId  is required");
    	        }
    		 else
    		 {
    			 orders = orderRepository.findByUserId(userId);
    		 }
             for(OrdersDTO order : orders){
                 List<OrderProductMap> products = orderProductMapRepository.getOrderProductMapByOrderId(order.getOrderId());
                 order.setProducts(products);
             }
             return orders;
    	 }
        catch (Exception e) {
            throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, "Exception while writing data to persistant layer", e);
        }
    }
    
    
    
    
    
    private Boolean validateOrder(OrdersDTO order) throws OrderException {
        if (order.getAddressId() == null || order.getAddressId().isEmpty()) {
            throw new OrderException(ErrorCode.BAD_DATA, "Valid Address Id is required");
        }
   
        if (order.getUserId() == null || order.getUserId().isEmpty()) {
            throw new OrderException(ErrorCode.BAD_DATA, "Valid User Id is required");
        }
        return true;
    }



}
