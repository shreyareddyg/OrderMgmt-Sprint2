package com.cg.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cg.Dto.CartDTO;

@Repository
public interface CartRepository extends CrudRepository<CartDTO, Integer> {

	
	List<CartDTO> findByUserId(String userId);

    void deleteByUserIdAndProductId(String userId, String productId);
}
