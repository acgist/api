package com.api.data.pojo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 管理员
 */
@Entity
@Table(name = "tb_admin")
@GenericGenerator(name = "sequenceGenerator", strategy = "uuid")
public class AdminEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	private String password;
	private String memo;

	@Column(length = 20, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 50, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(length = 100)
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
