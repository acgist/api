package com.api.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.data.pojo.entity.PermissionEntity;

/**
 * repository - 系统权限
 */
@Repository
public interface PermissionRepository extends BaseExtendRepository<PermissionEntity> {

	/**
	 * 根据权限ID查询子权限列表
	 * @param id 权限ID
	 * @return 子权限列表
	 */
	@Query(value = "select entity from PermissionEntity entity where entity.parent = :id")
	List<PermissionEntity> findChildren(String id);
	
}
