package com.acgist.gateway.pojo.entity;

import com.acgist.pojo.entity.BaseEntity;

public class GatewayEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String type;
	private String code;
	private String send;
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
