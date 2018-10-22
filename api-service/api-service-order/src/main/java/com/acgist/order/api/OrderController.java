package com.acgist.order.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acgist.config.APIConstURL;
import com.acgist.order.api.executor.PayExecutor;
import com.acgist.order.api.request.PayRequest;
import com.acgist.order.api.response.PayResponse;
import com.acgist.order.config.APIConstOrderURL;
import com.acgist.utils.APIUtils;

/**
 * 网关 - 订单
 */
@RestController
@RequestMapping(APIConstURL.URL_GATEWAY)
public class OrderController {

	@Autowired
	private ApplicationContext context;
	
	/**
	 * 订单支付
	 */
	@PostMapping(APIConstOrderURL.URL_ORDER_PAY)
	public PayResponse pay(@RequestBody PayRequest request) {
		return APIUtils.newInstance(context, PayExecutor.class).execute(request);
	}
	
}
