package com.api.data.pojo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 系统权限
 */
@Entity
@Table(name = "ts_permission", indexes = {
	@Index(name = "index_permission_parent", columnList = "parent")
})
@GenericGenerator(name = "sequenceGenerator", strategy = "uuid")
public class PermissionEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	private String pattern;
	private String memo;
	private String parent; // 父级菜单
	private Short sort;

	@Column(length = 20, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 50, nullable = false)
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	@Column(length = 100)
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(length = 32)
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Short getSort() {
		return sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

}
