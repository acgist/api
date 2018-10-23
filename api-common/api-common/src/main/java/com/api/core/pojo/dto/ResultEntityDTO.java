package com.api.core.pojo.dto;

import com.api.core.pojo.entity.BaseEntity;

/**
 * DTO：需要知道调用结果和实体的服务使用
 */
public class ResultEntityDTO<T extends BaseEntity> extends ResultDTO {

	private static final long serialVersionUID = 1L;

	protected T entity;

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

}
