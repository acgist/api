package com.acgist.gateway.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acgist.config.APIConstURL;
import com.acgist.gateway.api.executor.OrderExecutor;
import com.acgist.order.api.request.OrderRequest;
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
	 * 创建订单
	 */
	@PostMapping(APIConstURL.URL_ORDER)
	public Map<String, String> order(@RequestBody OrderRequest request) {
		return APIUtils.newInstance(context, OrderExecutor.class).response(request);
	}
	
}
