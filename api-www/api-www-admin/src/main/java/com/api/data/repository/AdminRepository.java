package com.api.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.data.pojo.entity.AdminEntity;

@Repository
public interface AdminRepository extends BaseExtendRepository<AdminEntity> {

	@Query(value = "SELECT * FROM ts_admin admin WHERE admin.name = :name LIMIT 1", nativeQuery = true)
	AdminEntity findName(String name);
	
}
