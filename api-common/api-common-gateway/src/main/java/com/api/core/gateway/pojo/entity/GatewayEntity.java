package com.api.core.gateway.pojo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.api.core.pojo.entity.BaseEntity;

/**
 * 网关记录
 */
@Entity
@Table(name = "tb_gateway")
@GenericGenerator(name = "sequenceGenerator", strategy = "uuid")
public class GatewayEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 参考：{@link APIType}
	 */
	private String type;
	/**
	 * 响应状态
	 */
	private String code;
	/**
	 * 请求ID
	 */
	private String queryId;
	/**
	 * 响应报文
	 */
	private String send;
	/**
	 * 请求报文
	 */
	private String receive;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

}
