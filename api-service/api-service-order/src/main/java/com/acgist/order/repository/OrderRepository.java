package com.acgist.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.acgist.order.pojo.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String>, JpaSpecificationExecutor<OrderEntity> {

}
