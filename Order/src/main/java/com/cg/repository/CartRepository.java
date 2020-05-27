package com.cg.repository;

import com.cg.Dto.Cart;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {

	
	List<Cart> findByUserId(String userId);

    void deleteByUserIdAndProductId(String userId, String productId);
}
