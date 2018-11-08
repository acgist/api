package com.api.data.user.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.data.repository.BaseExtendRepository;
import com.api.data.user.pojo.entity.UserEntity;

@Repository
public interface UserRepository extends BaseExtendRepository<UserEntity> {

	@Query(value = "SELECT * FROM tb_user user WHERE user.name = :name LIMIT 1", nativeQuery = true)
	UserEntity findName(String name);
	
}
