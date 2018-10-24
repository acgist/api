package com.api.data.user.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.data.repository.BaseRepository;
import com.api.data.user.pojo.entity.UserEntity;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {

	@Query("SELECT user FROM UserEntity user WHERE user.name = :name")
	UserEntity findName(String name);
	
}
