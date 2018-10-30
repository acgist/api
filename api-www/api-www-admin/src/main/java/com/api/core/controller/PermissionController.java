package com.api.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.core.pojo.vo.LayuiTable;
import com.api.data.pojo.entity.PermissionEntity;
import com.api.data.repository.PermissionRepository;

@Controller
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionRepository permissionRepository;
	
	@GetMapping("/list")
	public String listGet() {
		return "/permission/list";
	}
	
	@ResponseBody
	@PostMapping("/list")
	public LayuiTable listPost() {
		List<PermissionEntity> list = permissionRepository.findAll();
		return LayuiTable.build(list);
	}
	
}
