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

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 系统角色
 */
@Entity
@Table(name = "ts_role")
@GenericGenerator(name = "sequenceGenerator", strategy = "uuid")
public class RoleEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	public static final String PROPERTY_PERMISSIONS = "permissions";
	
	private String name;
	private String memo;
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
			foreignKey = @ForeignKey(name = "key_role_id")
		),
		inverseJoinColumns = @JoinColumn(
			name = "permission_id",
			referencedColumnName = "id",
			foreignKey = @ForeignKey(name = "key_permission_id")
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
