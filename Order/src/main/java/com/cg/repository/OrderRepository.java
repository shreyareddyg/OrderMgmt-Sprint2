package com.cg.repository;

import com.cg.Dto.CartDTO;
import com.cg.Dto.OrdersDTO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface OrderRepository extends CrudRepository<OrdersDTO, Integer> {

	List<OrdersDTO> findByUserId(String userId);

    void deleteByOrderId(String orderId);
}
