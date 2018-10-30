package com.api.core.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.core.pojo.vo.PermissionPackage;
import com.api.data.repository.PermissionRepository;

@Service
public class PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	public PermissionPackage tree() {
		PermissionPackage root = new PermissionPackage();
		root.setName("根节点");
		List<PermissionPackage> packages = permissionRepository
			.findAll()
			.stream()
			.map(value -> {
				PermissionPackage permissionPackage = new PermissionPackage();
				permissionPackage.setName(value.getName());
				permissionPackage.setEntity(value);
				return permissionPackage;
			})
			.collect(Collectors.toList());
		packagePermission(root, packages);
		return root;
	}

	private void packagePermission(PermissionPackage parent, List<PermissionPackage> list) {
		list.forEach(permission -> {
			if(parent.getEntity() == null && permission.getEntity().getParent() == null) {
				parent.addChildren(permission);
				packagePermission(permission, list);
			} else if(parent.getEntity() != null && parent.getEntity().getId().equals(permission.getEntity().getParent())) {
				parent.addChildren(permission);
				packagePermission(permission, list);
			}
		});
	}
	
}
