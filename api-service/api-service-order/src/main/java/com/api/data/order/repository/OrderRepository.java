package com.api.data.order.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.data.order.pojo.entity.OrderEntity;
import com.api.data.repository.BaseExtendRepository;

@Repository
public interface OrderRepository extends BaseExtendRepository<OrderEntity> {

	@Query(value = "SELECT * FROM tb_order model WHERE model.order_id = :orderId LIMIT 1", nativeQuery = true)
	OrderEntity findOrderId(String orderId);
	
}
