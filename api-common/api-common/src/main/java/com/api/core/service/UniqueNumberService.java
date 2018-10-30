package com.api.core.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 编号生成
 */
@Service
public class UniqueNumberService {

	@Value("${server.sn:10}")
	private String serverSN;

	private static final int MIN_INDEX = 100000;
	private static final int MAX_INDEX = 999999;
	private static int ID_INDEX = MIN_INDEX;
	
	/**
	 * 系统唯一编号
	 */
	public synchronized String buildId() {
		final StringBuffer builder = new StringBuffer();
		final SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmss");
		builder.append(formater.format(new Date()));
		builder.append(ID_INDEX);
		ID_INDEX++;
		if(ID_INDEX > MAX_INDEX) {
			ID_INDEX = MIN_INDEX;
		}
		return builder.toString();
	}
	
}
