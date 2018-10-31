package com.api.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.data.pojo.entity.PermissionEntity;

@Repository
public interface PermissionRepository extends BaseExtendRepository<PermissionEntity> {

	@Query(value = "select entity from PermissionEntity entity where entity.parent = :id")
	List<PermissionEntity> findChildren(String id);
	
}
