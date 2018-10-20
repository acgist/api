package com.acgist.user.service;

import org.springframework.cloud.openfeign.FeignClient;

import com.acgist.config.APIConstApplication;
import com.acgist.user.config.UserConfig;
import com.acgist.user.fallback.UserServiceFallback;

@FeignClient(value = APIConstApplication.API_SERVICE_USER, configuration = UserConfig.class, fallback = UserServiceFallback.class)
public interface UserService extends IUserService {

}
