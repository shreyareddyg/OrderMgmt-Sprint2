package com.cg.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.Dto.CartDTO;
import com.cg.exceptions.CartException;
import com.cg.exceptions.ErrorCode;
import com.cg.exceptions.OrderProjectException;
import com.cg.repository.CartRepository;

@Service
@Transactional
public class CartService {

	  @Autowired
	    private CartRepository cartRepository;

	    public void addProductToCart(CartDTO cart) throws CartException, OrderProjectException {
	        if (validateCart(cart)) {
	            try {
	                cartRepository.save(cart);
	            } catch (Exception e) {
	                throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, "Exception while writing data to persistant layer", e);
	            }
	        }
	    }

	    public List<CartDTO> findCartByUserId(String userId) throws CartException, OrderProjectException {
	        if (userId == null || userId.isEmpty()) {
	            throw new CartException(ErrorCode.BAD_DATA, "Valid User Id is required here");
	        }
	        try {
	            return cartRepository.findByUserId(userId);
	        } catch (Exception e) {
	            throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, "Exception while writing data to persistant layer", e);
	        }

	    }

	    public void deleteProductFromCart(String userId, String productId) throws CartException, OrderProjectException {
	        if (userId == null || userId.isEmpty()) {
	            throw new CartException(ErrorCode.BAD_DATA, "Valid User Id is required");
	        }
	        if (productId == null || productId.isEmpty()) {
	            throw new CartException(ErrorCode.BAD_DATA, "Valid Product Id is required");
	        }
	        try {
	            cartRepository.deleteByUserIdAndProductId(userId, productId);
	        } catch (Exception e) {
	            throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, "Exception while writing data to persistant layer", e);
	        }

	    }

	    public void updateCart(CartDTO cart) throws CartException, OrderProjectException {
	        if (validateCart(cart)) {
	            try {
	                cartRepository.deleteByUserIdAndProductId(cart.getUserId(), cart.getProductId());
	                cartRepository.save(cart);
	            } catch (Exception e) {
	                throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, "Exception while writing data to persistant layer", e);
	            }
	        }
	    }


	    private Boolean validateCart(CartDTO cart) {
	        if (cart.getQuantity() <= 0) {
	            throw new CartException(ErrorCode.BAD_DATA, "Product Quantity has be one or more");
	        }

	        if (cart.getUserId() == null || cart.getUserId().isEmpty()) {
	            throw new CartException(ErrorCode.BAD_DATA, "Valid User Id is required");
	        }
	        return true;
	    }

	    
		public void updateQuantity(CartDTO cart) {
			cartRepository.save(cart);
		}
}
