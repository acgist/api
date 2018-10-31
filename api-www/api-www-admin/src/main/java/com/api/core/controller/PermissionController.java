package com.api.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.core.gateway.APICode;
import com.api.core.pojo.dto.PermissionPackage;
import com.api.core.pojo.layui.LayuiMessage;
import com.api.core.service.PermissionService;
import com.api.data.pojo.entity.PermissionEntity;

@Controller
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@GetMapping("/tree")
	public String treeGet() {
		return "/permission/tree";
	}
	
	@ResponseBody
	@PostMapping("/tree")
	public List<PermissionPackage> treePost() {
		PermissionPackage permissionPackage = permissionService.tree();
		return permissionPackage.getChildren();
	}
	
	@ResponseBody
	@PostMapping("/submit")
	public PermissionEntity submit(PermissionEntity entity) {
		final String id = entity.getId();
		entity.setId(null);
		entity.setParent(id);
		return permissionService.submit(entity);
	}
	
	@ResponseBody
	@PostMapping("/update")
	public PermissionEntity update(PermissionEntity entity) {
		return permissionService.update(entity);
	}
	
	@ResponseBody
	@PostMapping("/delete")
	public LayuiMessage delete(String id) {
		if(permissionService.delete(id)) {
			return LayuiMessage.success();
		}
		return LayuiMessage.build(APICode.CODE_9999.getCode(), "不能删除含有子节点的权限");
	}
	
}
