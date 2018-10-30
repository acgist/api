package com.api.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.core.gateway.APICode;
import com.api.core.pojo.vo.LayuiMessage;
import com.api.core.pojo.vo.LayuiTable;
import com.api.data.pojo.entity.AdminEntity;
import com.api.data.pojo.select.Filter;
import com.api.data.pojo.select.PageQuery;
import com.api.data.pojo.select.PageResult;
import com.api.data.repository.AdminRepository;

/**
 * 管理员
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;
	
	@GetMapping("/info")
	public String info() {
		return "/admin/info";
	}

	@GetMapping("/list")
	public String listGet() {
		return "/admin/list";
	}
	
	@ResponseBody
	@PostMapping("/list")
	public LayuiTable listPost(int page, int limit, String id, String name) {
		PageQuery query = new PageQuery(page, limit);
		query.addFilters(Filter.eq("id", id), Filter.eq("name", name));
		PageResult<AdminEntity> pageResult = adminRepository.findPage(query);
		return LayuiTable.build(pageResult.getResult(), pageResult.getTotal());
	}

	@ResponseBody
	@PostMapping("/delete")
	public LayuiMessage delete(String id) {
		adminRepository.deleteById(id);
		return LayuiMessage.build(APICode.CODE_0000);
	}
	
}
