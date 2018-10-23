package com.api.core.order.repository;

import org.springframework.stereotype.Repository;

import com.api.core.order.pojo.entity.OrderEntity;
import com.api.core.repository.BaseExtendRepository;

@Repository
public interface OrderRepository extends BaseExtendRepository<OrderEntity> {

}
