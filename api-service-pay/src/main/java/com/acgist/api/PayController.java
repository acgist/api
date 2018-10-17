package com.acgist.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acgist.api.executor.PayExecutor;
import com.acgist.common.api.request.pay.PayRequest;
import com.acgist.common.core.APIConstURL;
import com.acgist.utils.APIUtils;

/**
 * 网关 - 交易
 */
@RestController
@RequestMapping(APIConstURL.URL_GATEWAY)
public class PayController {

	@Autowired
	private ApplicationContext context;
	
	/**
	 * 交易
	 */
	@PostMapping(APIConstURL.URL_PAY)
	public Map<String, String> pay(@RequestBody PayRequest payRequest) {
		return APIUtils.newInstance(context, PayExecutor.class).response(payRequest);
	}
	
}
