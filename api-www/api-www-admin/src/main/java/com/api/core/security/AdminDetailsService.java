package com.api.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.data.pojo.entity.AdminEntity;
import com.api.data.repository.AdminRepository;

/**
 * 权限验证
 */
@Service
public class AdminDetailsService implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminEntity admin = adminRepository.findName(username);
		if (admin == null) {
			throw new UsernameNotFoundException("用户不存在：" + username);
		}
		return new AdminDetails(admin);
	}

}
