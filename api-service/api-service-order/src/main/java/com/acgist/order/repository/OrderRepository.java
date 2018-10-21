package com.acgist.order.repository;

import org.springframework.stereotype.Repository;

import com.acgist.order.pojo.entity.OrderEntity;
import com.acgist.repository.BaseExtendRepository;

@Repository
public interface OrderRepository extends BaseExtendRepository<OrderEntity> {

}
