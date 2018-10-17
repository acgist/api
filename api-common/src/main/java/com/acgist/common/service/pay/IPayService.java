package com.acgist.common.service.pay;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acgist.common.core.APIConstURL;
import com.acgist.common.service.pay.pojo.Order;
import com.acgist.common.service.pay.pojo.PayResult;

/**
 * 交易服务，不能继承IPay，feign不支持多层继承
 */
@RequestMapping(APIConstURL.URL_SERVICE)
public interface IPayService {

	@PostMapping(APIConstURL.URL_PAY)
	PayResult pay(@RequestBody Order order);
	
}
