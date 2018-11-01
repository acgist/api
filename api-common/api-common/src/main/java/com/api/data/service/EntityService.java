package com.api.data.service;

import java.util.List;
import java.util.stream.Stream;

import com.api.data.pojo.entity.BaseEntity;
import com.api.data.pojo.select.PageQuery;
import com.api.data.pojo.select.PageResult;
import com.api.data.repository.BaseExtendRepository;

/**
 * 实体操作
 */
public class EntityService<T extends BaseEntity> implements APIEntityService {

	protected BaseExtendRepository<T> repository;
	
	public EntityService(BaseExtendRepository<T> repository) {
		this.repository = repository;
	}
	
	public T find(String id) {
		return repository.findOne(id);
	}
	
	public T submit(T t) {
		return repository.save(t);
	}
	
	public T update(T t) {
		return repository.save(t);
	}
	
	public boolean delete(String id) {
		repository.deleteById(id);
		return true;
	}
	
	public void delete(String ... ids) {
		Stream.of(ids).forEach(id -> delete(id));
	}
	
	public List<T> findAll() {
		return repository.findAll();
	}
	
	public PageResult<T> findPage(PageQuery pageQuery) {
		return repository.findPage(pageQuery);
	}
	
}
