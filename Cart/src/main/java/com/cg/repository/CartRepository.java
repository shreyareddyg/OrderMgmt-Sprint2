package com.cg.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cg.Dto.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {

	List<Cart> findByUserId(String userId);

    void deleteByUserIdAndProductId(String userId, String productId);

	Cart findByUserIdAndProductId(String userId, String productId);
}
