package com.api.data.order.repository;

import org.springframework.stereotype.Repository;

import com.api.data.order.pojo.entity.OrderEntity;
import com.api.data.repository.BaseExtendRepository;

@Repository
public interface OrderRepository extends BaseExtendRepository<OrderEntity> {

}
