package com.api.core.order.gateway.executor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.api.core.gateway.executor.APIExecutor;
import com.api.core.order.gateway.request.QueryRequest;
import com.api.core.order.gateway.response.QueryResponse;

/**
 * 订单查询
 */
@Component
@Scope("prototype")
public class QueryExecutor extends APIExecutor<QueryRequest, QueryResponse> {

	@Override
	protected void execute() {
		response.buildSuccess();
	}

}
