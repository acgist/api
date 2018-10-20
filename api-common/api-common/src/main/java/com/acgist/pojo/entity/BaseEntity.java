package com.acgist.pojo.entity;

import com.acgist.pojo.dto.BaseDTO;

/**
 * Entity：数据库实体
 */
public class BaseEntity extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
