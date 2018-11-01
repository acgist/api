package com.api.core.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.api.core.service.PermissionService;

/**
 * 路径过滤
 */
@Component
public class APISecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static final Logger LOGGER = LoggerFactory.getLogger(APISecurityMetadataSource.class);
	
	@Value("${system.security.allow.attributes:}")
	private String allowAttributes;
	
	@Autowired
	private PermissionService permissionService;
	
	private static final List<String> ALLOW_ATTRIBUTES = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		initAllowAttributes();
	}
	
	/**
	 * 获取访问角色
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		FilterInvocation filter = (FilterInvocation) object;
		if (allow(filter)) {
			return null; // return null = 不拦截
		}
		return allowAttributes(filter);
	}

	private boolean allow(FilterInvocation filter) {
		return ALLOW_ATTRIBUTES.stream()
			.filter(StringUtils::isNotEmpty)
			.map(AntPathRequestMatcher::new)
			.anyMatch(maptcher -> maptcher.matches(filter.getHttpRequest()));
	}
	
	private List<ConfigAttribute> allowAttributes(FilterInvocation filter) {
		List<String> list = permissionService.permissionRoles(filter.getRequest().getServletPath());
		if(list == null) {
			return deniedAttributes();
		}
		return list.stream()
			.map(role -> new SecurityConfig(role))
			.collect(Collectors.toList());
	}
	
	private List<ConfigAttribute> deniedAttributes() {
		return Collections.singletonList(new SecurityConfig("ROLE_DENIED"));
	}
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	private void initAllowAttributes() {
		LOGGER.info("不需要鉴权的地址：{}", this.allowAttributes);
		ALLOW_ATTRIBUTES.clear();
		ALLOW_ATTRIBUTES.addAll(Arrays.asList(this.allowAttributes.split(",")));
	}

}
