package com.api.data.gateway.repository;

import org.springframework.stereotype.Repository;

import com.api.data.gateway.pojo.entity.GatewayEntity;
import com.api.data.repository.BaseExtendRepository;

/**
 * repository - 网关信息
 */
@Repository
public interface GatewayRepository extends BaseExtendRepository<GatewayEntity> {

}
