package com.api.core.pojo.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.api.data.pojo.entity.PermissionEntity;

public class PermissionPackage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private PermissionEntity entity;
	private List<PermissionPackage> children;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PermissionEntity getEntity() {
		return entity;
	}

	public void setEntity(PermissionEntity entity) {
		this.entity = entity;
	}

	public List<PermissionPackage> getChildren() {
		return children;
	}

	public void setChildren(List<PermissionPackage> children) {
		this.children = children;
	}

	public void addChildren(PermissionPackage permissionPackage) {
		if(this.children == null) {
			this.children = new ArrayList<>();
		}
		this.children.add(permissionPackage);
	}
	
}
