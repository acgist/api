package com.api.core.repository;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import com.api.core.pojo.entity.BaseEntity;
import com.api.core.pojo.select.Filter;
import com.api.core.pojo.select.Order;

/**
 * 添加findAll和findPage方法，并且对其中方法进行优化，需要指定BaseExtendRepositoryImpl：
 * <code>
 * @EnableJpaRepositories(basePackages = "com.api.core.**.repository", repositoryBaseClass = BaseExtendRepositoryImpl.class)
 * </code>
 */
@NoRepositoryBean
@Transactional(readOnly = true)
public interface BaseExtendRepository<T extends BaseEntity> extends BaseRepository<T> {

	/**
	 * 第一条数据
	 */
	int FIRST = 0;

	/**
	 * 查询一条数据
	 */
	int SIZE_ONE = 1;

	/**
	 * 单个查询
	 * 
	 * @param filters 查询条件
	 */
	default T findOne(Filter... filters) {
		return findOne((List<Order>) null, filters);
	}

	/**
	 * 单个查询
	 * 
	 * @param order 查询排序
	 * @param filters 查询条件
	 */
	default T findOne(Order order, Filter... filters) {
		return findOne(Order.orders(order), filters);
	}

	/**
	 * 单个查询
	 * 
	 * @param orders 查询排序
	 * @param filters 查询条件
	 */
	default T findOne(List<Order> orders, Filter... filters) {
		List<T> list = findList(FIRST, SIZE_ONE, orders, filters);
		if(list == null || list.isEmpty()) {
			return null;
		}
		return list.get(FIRST);
	}

	/**
	 * 集合查询
	 * 
	 * @param size 查询数量
	 */
	default List<T> findList(int size) {
		return findList(FIRST, size, (List<Order>) null);
	}

	/**
	 * 集合查询
	 * 
	 * @param size 查询数量
	 * @param filters 查询条件
	 */
	default List<T> findList(int size, Filter... filters) {
		return findList(FIRST, size, (List<Order>) null, filters);
	}

	/**
	 * 集合查询
	 * 
	 * @param size 查询数量
	 * @param orders 查询排序
	 */
	default List<T> findList(int size, Order... orders) {
		return findList(FIRST, size, Order.orders(orders));
	}

	/**
	 * 集合查询
	 * 
	 * @param size 查询数量
	 * @param order 查询排序
	 * @param filters 查询条件
	 */
	default List<T> findList(int size, Order order, Filter... filters) {
		return findList(FIRST, size, Order.orders(order), filters);
	}

	/**
	 * 集合查询
	 * 
	 * @param size 查询数量
	 * @param orders 查询排序
	 * @param filters 查询条件
	 */
	default List<T> findList(int size, List<Order> orders, Filter... filters) {
		return findList(FIRST, size, orders, filters);
	}

	/**
	 * 集合查询
	 * 
	 * @param first 查询索引
	 * @param size 查询数量
	 */
	default List<T> findList(int first, int size) {
		return findList(first, size, (List<Order>) null);
	}

	/**
	 * 集合查询
	 * 
	 * @param first 查询索引
	 * @param size 查询数量
	 * @param filters 查询条件
	 */
	default List<T> findList(int first, int size, Filter... filters) {
		return findList(first, size, (List<Order>) null, filters);
	}

	/**
	 * 集合查询
	 * 
	 * @param first 查询索引
	 * @param size 查询数量
	 * @param orders 查询排序
	 */
	default List<T> findList(int first, int size, Order... orders) {
		return findList(first, size, Order.orders(orders));
	}

	/**
	 * 集合查询
	 * 
	 * @param first 查询索引
	 * @param size 查询数量
	 * @param order 查询排序
	 * @param filters 查询条件
	 */
	default List<T> findList(int first, int size, Order order, Filter... filters) {
		return findList(first, size, Order.orders(order), filters);
	}

	/**
	 * 集合查询
	 * 
	 * @param first 查询索引
	 * @param size 查询数量
	 * @param orders 查询排序
	 * @param filters 查询条件
	 */
	List<T> findList(int first, int size, List<Order> orders, Filter... filters);

}
