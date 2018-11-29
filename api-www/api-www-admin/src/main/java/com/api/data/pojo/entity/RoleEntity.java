package com.api.data.pojo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * entity - 系统角色
 */
@Entity
@Table(name = "ts_role")
public class RoleEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	public static final String PROPERTY_PERMISSIONS = "permissions";
	
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 描述
	 */
	private String memo;
	/**
	 * 权限
	 */
	@JsonIgnore
	private List<PermissionEntity> permissions;

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

	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(
		name = "ts_role_permission",
		joinColumns = @JoinColumn(
			name = "role_id",
			referencedColumnName = "id",
			foreignKey = @ForeignKey(name = "key_role_permission_role_id")
		),
		inverseJoinColumns = @JoinColumn(
			name = "permission_id",
			referencedColumnName = "id",
			foreignKey = @ForeignKey(name = "key_role_permission_permission_id")
		)
	)
	public List<PermissionEntity> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionEntity> permissions) {
		this.permissions = permissions;
	}

	public boolean hasPermission(PermissionEntity permission) {
		if(permissions == null) {
			return false;
		}
		return permissions.contains(permission);
	}
	
}
