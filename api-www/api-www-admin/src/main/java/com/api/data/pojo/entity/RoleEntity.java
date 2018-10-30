package com.api.data.pojo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 管理员角色
 */
@Entity
@Table(name = "tb_role")
@GenericGenerator(name = "sequenceGenerator", strategy = "uuid")
public class RoleEntity {

	private String name;
	private String memo;

	@Column(length = 20, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 100)
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
