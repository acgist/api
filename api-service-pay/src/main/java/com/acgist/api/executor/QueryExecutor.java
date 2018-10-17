package com.acgist.api.executor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.acgist.common.api.executor.APIExecutor;
import com.acgist.common.api.request.pay.QueryRequest;
import com.acgist.common.api.response.pay.QueryResponse;

/**
 * 交易查询
 */
@Component
@Scope("prototype")
public class QueryExecutor extends APIExecutor<QueryRequest, QueryResponse> {

	@Override
	protected void execute() {
		response.success();
	}

}
