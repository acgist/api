package com.api.data.repository;

import org.springframework.stereotype.Repository;

import com.api.data.user.pojo.entity.UserEntity;

@Repository
public interface UserRepository extends BaseExtendRepository<UserEntity> {

}
