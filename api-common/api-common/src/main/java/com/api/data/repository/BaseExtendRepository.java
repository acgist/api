package com.api.data.repository;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import com.api.data.pojo.entity.BaseEntity;
import com.api.data.pojo.select.Filter;
import com.api.data.pojo.select.Order;

/**
 * 添加findAll和findPage方法，并且对其中方法进行优化，需要指定BaseExtendRepositoryImpl：
 * <code>
 * @EnableJpaRepositories(basePackages = "com.api.core.**.repository", repositoryBaseClass = BaseExtendRepositoryImpl.class)
 * </code>
 */
@NoRepositoryBean
@Transactional(readOnly = true)
public interface BaseExtendRepository<T extends BaseEntity> extends BaseRepository<T> {

	// 更新时忽略属性
	static final String[] UPDATE_IGNORE_PROPERTIES = new String[] {
		BaseEntity.PROPERTY_CLASS,
		BaseEntity.PROPERTY_ID,
		BaseEntity.PROPERTY_CREATE_DATE,
		BaseEntity.PROPERTY_MODIFY_DATE
	};
	
	static final Logger LOGGER = LoggerFactory.getLogger(BaseExtendRepository.class);
	
	/**
	 * 第一条数据
	 */
	int FIRST = 0;

	/**
	 * 查询一条数据
	 */
	int SIZE_ONE = 1;

	/**
	 * 根据ID查询
	 */
	default T findOne(String id) {
		return findOne(Filter.eq(BaseEntity.PROPERTY_ID, id));
	}
	
	/**
	 * 单个查询
	 * 
	 * @param filters 查询条件
	 */
	default T findOne(Filter ... filters) {
		return findOne((List<Order>) null, filters);
	}

	/**
	 * 单个查询
	 * 
	 * @param order 查询排序
	 * @param filters 查询条件
	 */
	default T findOne(Order order, Filter ... filters) {
		return findOne(Order.orders(order), filters);
	}

	/**
	 * 单个查询
	 * 
	 * @param orders 查询排序
	 * @param filters 查询条件
	 */
	default T findOne(List<Order> orders, Filter ... filters) {
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
	default List<T> findList(int size, Filter ... filters) {
		return findList(FIRST, size, (List<Order>) null, filters);
	}

	/**
	 * 集合查询
	 * 
	 * @param size 查询数量
	 * @param orders 查询排序
	 */
	default List<T> findList(int size, Order ... orders) {
		return findList(FIRST, size, Order.orders(orders));
	}

	/**
	 * 集合查询
	 * 
	 * @param size 查询数量
	 * @param order 查询排序
	 * @param filters 查询条件
	 */
	default List<T> findList(int size, Order order, Filter ... filters) {
		return findList(FIRST, size, Order.orders(order), filters);
	}

	/**
	 * 集合查询
	 * 
	 * @param size 查询数量
	 * @param orders 查询排序
	 * @param filters 查询条件
	 */
	default List<T> findList(int size, List<Order> orders, Filter ... filters) {
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
	default List<T> findList(int first, int size, Filter ... filters) {
		return findList(first, size, (List<Order>) null, filters);
	}

	/**
	 * 集合查询
	 * 
	 * @param first 查询索引
	 * @param size 查询数量
	 * @param orders 查询排序
	 */
	default List<T> findList(int first, int size, Order ... orders) {
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
	default List<T> findList(int first, int size, Order order, Filter ... filters) {
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
	List<T> findList(int first, int size, List<Order> orders, Filter ... filters);

	/**
	 * 更新
	 * @param t 更新实体
	 * @param fields 忽略字段
	 */
	default T update(T t, String ... ignoreProperties) {
		T persistant = findOne(t.getId());
		if (persistant != null) {
			copyProperties(t, persistant, (String[]) ArrayUtils.addAll(ignoreProperties, UPDATE_IGNORE_PROPERTIES));
			return save(persistant);
		} else {
			return saveAndFlush(persistant);
		}
	}
	
	/**
	 * 拷贝属性
	 * @param source 提供属性的数据源
	 * @param target 设置属性的数据源
	 * @param ignoreProperties 忽略属性
	 * @throws BeansException beans拷贝异常
	 */
	private void copyProperties(Object source, Object target, String[] ignoreProperties) throws BeansException {
		final PropertyDescriptor[] propertys = PropertyUtils.getPropertyDescriptors(target.getClass());
		Stream.of(propertys)
		.filter(property -> property.getWriteMethod() != null)
		.filter(property -> ignoreProperties == null || !ArrayUtils.contains(ignoreProperties, property.getName()))
		.forEach(property -> {
			try {
				property.getWriteMethod().invoke(target, property.getReadMethod().invoke(source));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				LOGGER.info("属性拷贝异常", e);
			}
		});
	}
	
}
