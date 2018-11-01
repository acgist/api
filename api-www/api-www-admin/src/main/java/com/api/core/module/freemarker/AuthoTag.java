package com.api.core.module.freemarker;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.api.core.security.AdminDetails;
import com.api.core.service.PermissionService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 权限标签
 */
@Component
public class AuthoTag implements TemplateDirectiveModel {
	
	private static final String KEY_NAME = "name"; // 菜单
	private static final String KEY_PATTERN = "pattern"; // 菜单路径
	
	@Autowired
	private PermissionService permissionService;
	
	@Override
	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] model, TemplateDirectiveBody body) throws TemplateException, IOException {
		String name = (String) params.get(KEY_NAME);
		String pattern = (String) params.get(KEY_PATTERN);
		if(!(name(name) || pattern(pattern))) {
			return;
		}
		body.render(env.getOut());
	}

	private boolean name(String name) {
		if(name == null) {
			return false;
		}
		AdminDetails adminDetails = AdminDetails.current();
		return adminDetails.hasPermissions(name);
	}
	
	private boolean pattern(String pattern) {
		if(pattern == null) {
			return false;
		}
		List<String> roles = permissionService.permissionRoles(pattern);
		if(roles == null) {
			return false;
		}
		AdminDetails adminDetails = AdminDetails.current();
		return adminDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(role -> roles.contains(role));
	}
	
}
