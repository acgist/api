package com.acgist.service;

import org.springframework.cloud.openfeign.FeignClient;

import com.acgist.common.core.APIConstApplication;
import com.acgist.common.service.pay.IPayService;
import com.acgist.config.PayConfig;

@FeignClient(value = APIConstApplication.API_SERVICE_PAY, configuration = PayConfig.class, fallback = PayServiceFallback.class)
public interface PayService extends IPayService {

}
