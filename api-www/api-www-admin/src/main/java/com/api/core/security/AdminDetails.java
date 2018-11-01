package com.api.core.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.data.pojo.entity.AdminEntity;
import com.api.data.pojo.entity.PermissionEntity;
import com.api.data.pojo.entity.RoleEntity;

/**
 * 系统用户
 */
public class AdminDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String name;
	private String password;
	private List<String> permissions; // 菜单名称
	private List<SimpleGrantedAuthority> authorities; // 用户角色
	
	public AdminDetails(AdminEntity admin) {
		this.name = admin.getName();
		this.password = admin.getPassword();
		this.permissions = admin.getRoles().stream()
			.map(role -> role.getPermissions())
			.flatMap(list -> list.stream())
			.map(PermissionEntity::getName)
			.collect(Collectors.toList());
		this.authorities = admin.getRoles()
			.stream()
			.map(RoleEntity::getName)
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toList());
	}

	/**
	 * 系统角色
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	
	@Override
	public String getUsername() {
		return this.name;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * 判断是否有权限
	 */
	public boolean hasPermissions(String permission) {
		if(this.permissions == null) {
			return false;
		}
		return this.permissions.contains(permission);
	}
	
	/**
	 * 获取当前系统用户
	 */
	public static final AdminDetails current() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if(authentication.isAuthenticated()) {
			return (AdminDetails) authentication.getPrincipal();
		}
		return null;
	}
	
}
