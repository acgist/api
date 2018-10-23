package com.api.core.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import com.api.core.pojo.entity.BaseEntity;
import com.api.core.pojo.select.Filter;
import com.api.core.pojo.select.Order;
import com.api.core.pojo.select.PageQuery;
import com.api.core.pojo.select.PageResult;
import com.api.core.pojo.select.Filter.Operator;

/**
 * 添加findAll和findPage方法，直接使用接口实现即可使用
 */
@NoRepositoryBean
@Transactional(readOnly = true)
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, String>, JpaSpecificationExecutor<T>  {

	/**
	 * 集合查询
	 * @param filters 查询条件
	 */
	default List<T> findAll(Filter ... filters) {
		return findAll((List<Order>) null, filters);
	}
	
	/**
	 * 集合查询
	 * @param order 查询排序
	 * @param filters 查询条件
	 */
	default List<T> findAll(Order order, Filter ... filters) {
		return findAll(Order.orders(order), filters);
	}
	
	/**
	 * 集合查询
	 * @param orders 查询排序
	 * @param filters 查询条件
	 */
	default List<T> findAll(List<Order> orders, Filter ... filters) {
		return findAll(new Specification<T>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				buildCriteriaQuery(Filter.filters(filters), orders, root, criteriaQuery, criteriaBuilder);
				return null;
			}
		});
	}
	
	/**
	 * 分页查询
	 * @param pageQuery 分页信息
	 * @return 分页结果
	 */
	default PageResult<T> findPage(PageQuery pageQuery) {
		Page<T> result = findAll(new Specification<T>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				buildCriteriaQuery(pageQuery.getFilters(), pageQuery.getOrders(), root, criteriaQuery, criteriaBuilder);
				return null;
			}
		}, PageRequest.of(pageQuery.getPage(), pageQuery.getPageSize()));
		return new PageResult<>(result.getContent(), result.getTotalElements(), pageQuery);
	}
	
	/**
	 * 创建查询条件
	 * @param filters 查询条件
	 * @param orders 排序条件
	 */
	default void buildCriteriaQuery(List<Filter> filters, List<Order> orders, Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
		if(filters != null && !filters.isEmpty()) {
			List<Predicate> list = new ArrayList<>(filters.size());
			filters
			.stream()
			.filter(filter -> {
				return filter != null && filter.getProperty() != null;
			})
			.forEach(filter -> {
				if (filter.getOperator() == Operator.eq && filter.getValue() != null) {
					list.add(criteriaBuilder.equal(root.get(filter.getProperty()), filter.getValue()));
				} else if (filter.getOperator() == Operator.ne && filter.getValue() != null) {
					list.add(criteriaBuilder.notEqual(root.get(filter.getProperty()), filter.getValue()));
				} else if (filter.getOperator() == Operator.gt && filter.getValue() != null) {
					if(filter.getValue() instanceof Date) {
						list.add(criteriaBuilder.greaterThan(root.<Date>get(filter.getProperty()), (Date) filter.getValue()));
					} else {
						list.add(criteriaBuilder.gt(root.<Number>get(filter.getProperty()), (Number) filter.getValue()));
					}
				} else if (filter.getOperator() == Operator.lt && filter.getValue() != null) {
					if(filter.getValue() instanceof Date) {
						list.add(criteriaBuilder.lessThan(root.<Date>get(filter.getProperty()), (Date) filter.getValue()));
					} else {
						list.add(criteriaBuilder.lt(root.<Number>get(filter.getProperty()), (Number) filter.getValue()));
					}
				} else if (filter.getOperator() == Operator.ge && filter.getValue() != null) {
					if(filter.getValue() instanceof Date) {
						list.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get(filter.getProperty()), (Date) filter.getValue()));
					} else {
						list.add(criteriaBuilder.ge(root.<Number>get(filter.getProperty()), (Number) filter.getValue()));
					}
				} else if (filter.getOperator() == Operator.le && filter.getValue() != null) {
					if(filter.getValue() instanceof Date) {
						list.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get(filter.getProperty()), (Date) filter.getValue()));
					} else {
						list.add(criteriaBuilder.le(root.<Number>get(filter.getProperty()), (Number) filter.getValue()));
					}
				} else if (filter.getOperator() == Operator.like && filter.getValue() != null && filter.getValue() instanceof String) {
					list.add(criteriaBuilder.like(root.<String>get(filter.getProperty()), (String) filter.getValue()));
				} else if (filter.getOperator() == Operator.in && filter.getValue() != null) {
					list.add(root.get(filter.getProperty()).in(filter.getValue()));
				} else if (filter.getOperator() == Operator.isNull) {
					list.add(root.get(filter.getProperty()).isNull());
				} else if (filter.getOperator() == Operator.isNotNull) {
					list.add(root.get(filter.getProperty()).isNotNull());
				}
			});
//			OR筛选
//			criteriaQuery.where(criteriaBuilder.or(list.toArray(new Predicate[list.size()])));
//			AND筛选
//			criteriaQuery.where(criteriaBuilder.and(list.toArray(new Predicate[list.size()])));
			criteriaQuery.where(list.toArray(new Predicate[list.size()]));
		}
		if(orders != null && !orders.isEmpty()) {
			List<javax.persistence.criteria.Order> list = new ArrayList<>(orders.size());
			orders
			.stream()
			.filter(order -> {
				return order != null && order.getProperty() != null;
			})
			.forEach(order -> {
				if (order.getDirection() == Order.Direction.asc) {
					list.add(criteriaBuilder.asc(root.get(order.getProperty())));
				} else if (order.getDirection() == Order.Direction.desc) {
					list.add(criteriaBuilder.desc(root.get(order.getProperty())));
				}
			});
			criteriaQuery.orderBy(list);
		}
	}
	
}
