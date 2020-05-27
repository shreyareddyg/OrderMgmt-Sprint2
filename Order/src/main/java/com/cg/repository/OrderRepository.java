package com.cg.repository;

import com.cg.Dto.Orders;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface OrderRepository extends CrudRepository<Orders, Integer> {

	List<Orders> findByUserId(String userId);

    void deleteByOrderId(String orderId);
}
