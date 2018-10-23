package com.api.feign.user.service;

import org.springframework.cloud.openfeign.FeignClient;

import com.api.core.config.APIConstApplication;
import com.api.core.user.service.IUserService;
import com.api.feign.user.config.UserConfig;
import com.api.feign.user.fallback.UserServiceFallback;

@FeignClient(value = APIConstApplication.API_SERVICE_USER, configuration = UserConfig.class, fallback = UserServiceFallback.class)
public interface UserService extends IUserService {

}
