package com.acgist.order.api.executor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.acgist.api.executor.APIExecutor;
import com.acgist.order.api.request.QueryRequest;
import com.acgist.order.api.response.QueryResponse;

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
