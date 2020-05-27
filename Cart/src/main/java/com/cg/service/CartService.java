package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.Dto.Cart;
import com.cg.exceptions.CartException;
import com.cg.exceptions.ErrorCode;
import com.cg.exceptions.OrderProjectException;
import com.cg.repository.CartRepository;

@Service
@Transactional
public class CartService implements ICartService{

	private static final String ACTION_1 = "Exception while writing data to persistant layer";  
	  @Autowired
	    private CartRepository cartRepository;
	  
	  /*******************************************************************************************************
		 * Function Name : addProductToCart 
		 * Input Parameters : Cart(userId, productId, quantity) 
		 * Return Type : void 
		 * Throws : CartException, OrderProjectException
		 * Description : to add product to a cart
		 ********************************************************************************************************/
	    public void addProductToCart(Cart cart) {
	    	if (validateCart(cart)) {
	            try {
	                Cart c = cartRepository.findByUserIdAndProductId(cart.getUserId(),cart.getProductId());
	                if(c != null) {
	                    int quantity = c.getQuantity();
	                    c.setQuantity(quantity + 1);
	                    cartRepository.save(c);
	                }else {
	                    cartRepository.save(cart);
	                }
	            } catch (Exception e) {
	                throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION,ACTION_1, e);
	            }
	        }
	    }
	    
	    /*******************************************************************************************************
		 * Function Name : findCartByUserId 
		 * Input Parameters : userId
		 * Return Type : List 
		 * Throws : CartException, OrderProjectException
		 * Description : to find the cart to particular userId
		 ********************************************************************************************************/

	   	    public List<Cart> findCartByUserId(String userId){
	        if (userId == null || userId.isEmpty()) {
	            throw new CartException(ErrorCode.BAD_DATA, "Valid User Id is required here");
	        }
	        try {
	            return cartRepository.findByUserId(userId);
	        } catch (Exception e) {
	            throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, ACTION_1, e);
	        }
	    }

	   	 /*******************************************************************************************************
			 * Function Name : deleteProductFromCart 
			 * Input Parameters : userId, productId 
			 * Return Type : void 
			 * Throws : CartException, OrderProjectException
			 * Description : to delete product from the cart
			 ********************************************************************************************************/

	    public void deleteProductFromCart(String userId, String productId)  {
	        if (userId == null || userId.isEmpty()) {
	            throw new CartException(ErrorCode.BAD_DATA, "Valid User Id is required");
	        }
	        if (productId == null || productId.isEmpty()) {
	            throw new CartException(ErrorCode.BAD_DATA, "Valid Product Id is required");
	        }
	        try {
	            Cart item = cartRepository.findByUserIdAndProductId(userId, productId);
	            if(item.getQuantity() == 1) {
	                cartRepository.deleteByUserIdAndProductId(userId, productId);
	            }else {
	                item.setQuantity(item.getQuantity() - 1);
	                cartRepository.save(item);
	            }
	        } catch (Exception e) {
	            throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION,ACTION_1, e);
	        }

	    }
	    
	    /*******************************************************************************************************
		 * Function Name : updateCart 
		 * Input Parameters : Cart(userId, productId, quantity)
		 * Return Type : void 
		 * Throws : CartException, OrderProjectException
		 * Description : to update the cart
		 ********************************************************************************************************/

	    public void updateCart(Cart cart) {
	        if (validateCart(cart)) {
	            try {
	                cartRepository.deleteByUserIdAndProductId(cart.getUserId(), cart.getProductId());
	                cartRepository.save(cart);
	            } catch (Exception e) {
	                throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, ACTION_1, e);
	            }
	        }
	    }

	    /*******************************************************************************************************
	  		 * Function Name : validateCart 
	  		 * Input Parameters : Cart(userId, productId, quantity)
	  		 * Return Type : boolean 
	  		 * Throws : CartException, OrderProjectException
	  		 * Description : to validate the cart
	  		 ********************************************************************************************************/
	    private Boolean validateCart(Cart cart) {
	        if (cart.getQuantity() <= 0) {
	            throw new CartException(ErrorCode.BAD_DATA, "Product Quantity has to be one or more");
	        }

	        if (cart.getUserId() == null || cart.getUserId().isEmpty()) {
	            throw new CartException(ErrorCode.BAD_DATA, "Valid User Id is required");
	        }
	        return true;
	    }

	
}
