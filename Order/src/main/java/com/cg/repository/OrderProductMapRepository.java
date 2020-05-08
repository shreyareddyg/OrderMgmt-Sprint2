package com.cg.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.Dto.OrderProductMap;

import java.util.List;


@Repository
public interface OrderProductMapRepository extends CrudRepository<OrderProductMap, Integer> {

	void deleteByOrderId(String orderId);
	
	List<OrderProductMap>  getOrderProductMapByOrderId(String orderId);
	
}
