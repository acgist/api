package com.api.core.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.core.pojo.dto.PermissionPackage;
import com.api.data.pojo.entity.PermissionEntity;
import com.api.data.repository.PermissionRepository;
import com.api.data.service.EntityService;

@Service
public class PermissionService extends EntityService<PermissionEntity> {

	@Autowired
	public PermissionService(PermissionRepository repository) {
		super(repository);
	}
	
	public PermissionPackage tree() {
		return tree(null);
	}
	
	public PermissionPackage tree(List<PermissionEntity> list) {
		PermissionPackage root = new PermissionPackage();
		root.setName("根节点");
		List<PermissionPackage> packages = repository
			.findAll()
			.stream()
			.sorted((a, b) -> {
				return (a.getSort() != null || b.getSort() != null) ? 0 : a.getSort().compareTo(b.getSort());
			})
			.map(value -> {
				PermissionPackage permissionPackage = new PermissionPackage();
				permissionPackage.setName(value.getName());
				permissionPackage.setEntity(value);
				if(list != null) {
					permissionPackage.setChecked(list.stream().anyMatch(entity -> entity.getId().equals(value.getId())));
				} else {
					permissionPackage.setChecked(Boolean.FALSE);
				}
				return permissionPackage;
			})
			.collect(Collectors.toList());
		packagePermission(root, packages);
		return root;
	}

	/**
	 * 递归权限树
	 */
	private void packagePermission(PermissionPackage parent, List<PermissionPackage> list) {
		list.forEach(permission -> {
			final PermissionEntity parentEntity = parent.getEntity();
			final PermissionEntity permissionEntity = permission.getEntity();
			if(parentEntity == null && StringUtils.isEmpty(permissionEntity.getParent())) {
				parent.addChildren(permission);
				packagePermission(permission, list);
			} else if(parentEntity != null && parentEntity.getId().equals(permissionEntity.getParent())) {
				parent.addChildren(permission);
				packagePermission(permission, list);
			}
		});
	}
	
	@Override
	public boolean delete(String id) {
		PermissionRepository permissionRepository = (PermissionRepository) repository;
		List<PermissionEntity> list = permissionRepository.findChildren(id);
		if(list.isEmpty()) {
			return super.delete(id);
		}
		return false;
	}
	
}
