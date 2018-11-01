package com.api.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import com.api.core.pojo.dto.PermissionPackage;
import com.api.data.pojo.entity.PermissionEntity;
import com.api.data.pojo.entity.RoleEntity;
import com.api.data.repository.PermissionRepository;
import com.api.data.repository.RoleRepository;
import com.api.data.service.EntityService;

@Service
public class PermissionService extends EntityService<PermissionEntity> {

	private static final Map<String, List<String>> PERMISSION_ROLES = new HashMap<>();
	private static final Logger LOGGER = LoggerFactory.getLogger(PermissionService.class);
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	public PermissionService(PermissionRepository repository) {
		super(repository);
	}

	@PostConstruct
	public void init() {
		initPermissionRoles();
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
	
	private void initPermissionRoles() {
		LOGGER.info("初始化权限和角色映射信息");
		PERMISSION_ROLES.clear();
		Map<String, String> permissions = new HashMap<>();
		List<RoleEntity> list = roleRepository.findAll();
		list.forEach(role -> {
			role.getPermissions().stream()
			.filter(permission -> StringUtils.isNotEmpty(permission.getPattern()))
			.forEach(permission -> {
				permissions.put(permission.getPattern(), role.getName());
			});
		});
		Map<String, List<String>> result = permissions.entrySet().stream()
			.collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
		PERMISSION_ROLES.putAll(result);
		LOGGER.info("权限和角色映射信息：{}", PERMISSION_ROLES);
	}
	
	public List<String> permissionRoles(String servletPath) {
		Optional<Entry<String, List<String>>> equalMatch = PERMISSION_ROLES.entrySet().stream()
			.filter(entity -> entity.getKey().equalsIgnoreCase(servletPath))
			.findFirst();
		if(equalMatch.isPresent()) {
			equalMatch.get().getValue();
		}
		Optional<Entry<String, List<String>>> antMatch = PERMISSION_ROLES.entrySet().stream()
			.filter(entity -> {
				AntPathMatcher matcher = new AntPathMatcher();
				return matcher.match(entity.getKey(), servletPath);
			})
			.findFirst();
		return antMatch.isPresent() ? antMatch.get().getValue() : null;
	}
	
}
