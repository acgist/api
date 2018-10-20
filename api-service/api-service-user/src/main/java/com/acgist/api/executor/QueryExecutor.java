package com.acgist.api.executor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.acgist.api.request.order.QueryRequest;
import com.acgist.api.response.order.QueryResponse;

/**
 * 订单查询
 */
@Component
@Scope("prototype")
public class QueryExecutor extends APIExecutor<QueryRequest, QueryResponse> {

	@Override
	protected void execute() {
		response.success();
	}

}
