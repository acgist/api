package com.api.data.pojo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 系统用户
 */
@Entity
@Table(name = "ts_admin", indexes = {
	@Index(name = "index_admin_name", columnList = "name", unique = true)
})
@GenericGenerator(name = "sequenceGenerator", strategy = "uuid")
public class AdminEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public static final String NAME_PROPERTY_NAME = "name";
	public static final String ROLES_PROPERTY_NAME = "roles";
	
	private String name;
	private String password;
	private String memo;
	@JsonIgnore
	private List<RoleEntity> roles;

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

	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(
		name = "ts_admin_role",
		joinColumns = @JoinColumn(
			name = "admin_id",
			referencedColumnName = "id",
			foreignKey = @ForeignKey(name = "key_admin_id")
		),
		inverseJoinColumns = @JoinColumn(
			name = "role_id",
			referencedColumnName = "id",
			foreignKey = @ForeignKey(name = "key_role_id")
		)
	)
	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}
	
	public boolean hasRole(RoleEntity role) {
		if(this.roles == null) {
			return false;
		}
		return roles.contains(role);
	}
	
	public boolean hasPermission(PermissionEntity permission) {
		if(roles == null) {
			return false;
		}
		return roles
			.stream()
			.anyMatch(role -> role.hasPermission(permission));
	}
	
}
