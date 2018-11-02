package com.api.data.service;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional(readOnly = true)
	public T find(String id) {
		return repository.findOne(id);
	}
	
	@Transactional
	public T submit(T t) {
		return repository.save(t);
	}
	
	@Transactional
	public T update(T t) {
		return repository.save(t);
	}
	
	@Transactional
	public boolean delete(String id) {
		repository.deleteById(id);
		return true;
	}
	
	@Transactional
	public void delete(String ... ids) {
		Stream.of(ids).forEach(id -> delete(id));
	}
	
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public PageResult<T> findPage(PageQuery pageQuery) {
		return repository.findPage(pageQuery);
	}
	
}
