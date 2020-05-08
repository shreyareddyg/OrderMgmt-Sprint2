package com.cg.repository;

import com.cg.Dto.OrdersDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface OrderRepository extends CrudRepository<OrdersDTO, Integer> {

    OrdersDTO findByOrderId(String orderId);

    void deleteByOrderId(String orderId);
}
