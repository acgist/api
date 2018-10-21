package com.acgist.user.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.acgist.pojo.entity.BaseEntity;
import com.acgist.pojo.select.Filter;
import com.acgist.pojo.select.Filter.Operator;
import com.acgist.pojo.select.Order;
import com.acgist.pojo.select.PageQuery;
import com.acgist.pojo.select.PageResult;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, String>, JpaSpecificationExecutor<T>  {

	default PageResult<T> findPage(PageQuery pageQuery) {
		findAll(new Specification<T>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				addRestrictions(pageQuery.getFilters(), pageQuery.getOrders(), root, criteriaQuery, criteriaBuilder);
				return null;
			}
		}, PageRequest.of(pageQuery.getPage(), pageQuery.getPageSize()));
		return null;
	}
	
	default void addRestrictions(List<Filter> filters, List<Order> orders, Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
		if(filters != null && !filters.isEmpty()) {
			Predicate predicate = criteriaBuilder.conjunction();
			filters
			.stream()
			.filter(filter -> {
				return filter != null && filter.getProperty() != null;
			})
			.forEach(filter -> {
				if (filter.getOperator() == Operator.eq && filter.getValue() != null) {
					criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(filter.getProperty()), filter.getValue()));
				} else if (filter.getOperator() == Operator.ne && filter.getValue() != null) {
					criteriaBuilder.and(predicate, criteriaBuilder.notEqual(root.get(filter.getProperty()), filter.getValue()));
				} else if (filter.getOperator() == Operator.gt && filter.getValue() != null) {
					if(filter.getValue() instanceof Date) {
						criteriaBuilder.and(predicate, criteriaBuilder.greaterThan(root.<Date>get(filter.getProperty()), (Date) filter.getValue()));
					} else {
						criteriaBuilder.and(predicate, criteriaBuilder.gt(root.<Number>get(filter.getProperty()), (Number) filter.getValue()));
					}
				} else if (filter.getOperator() == Operator.lt && filter.getValue() != null) {
					if(filter.getValue() instanceof Date) {
						criteriaBuilder.and(predicate, criteriaBuilder.lessThan(root.<Date>get(filter.getProperty()), (Date) filter.getValue()));
					} else {
						criteriaBuilder.and(predicate, criteriaBuilder.lt(root.<Number>get(filter.getProperty()), (Number) filter.getValue()));
					}
				} else if (filter.getOperator() == Operator.ge && filter.getValue() != null) {
					if(filter.getValue() instanceof Date) {
						criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.<Date>get(filter.getProperty()), (Date) filter.getValue()));
					} else {
						criteriaBuilder.and(predicate, criteriaBuilder.ge(root.<Number>get(filter.getProperty()), (Number) filter.getValue()));
					}
				} else if (filter.getOperator() == Operator.le && filter.getValue() != null) {
					if(filter.getValue() instanceof Date) {
						criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.<Date>get(filter.getProperty()), (Date) filter.getValue()));
					} else {
						criteriaBuilder.and(predicate, criteriaBuilder.le(root.<Number>get(filter.getProperty()), (Number) filter.getValue()));
					}
				} else if (filter.getOperator() == Operator.like && filter.getValue() != null && filter.getValue() instanceof String) {
					criteriaBuilder.and(predicate, criteriaBuilder.like(root.<String>get(filter.getProperty()), (String) filter.getValue()));
				} else if (filter.getOperator() == Operator.in && filter.getValue() != null) {
					criteriaBuilder.and(predicate, root.get(filter.getProperty()).in(filter.getValue()));
				} else if (filter.getOperator() == Operator.isNull) {
					criteriaBuilder.and(predicate, root.get(filter.getProperty()).isNull());
				} else if (filter.getOperator() == Operator.isNotNull) {
					criteriaBuilder.and(predicate, root.get(filter.getProperty()).isNotNull());
				}
			});
			criteriaQuery.where(predicate);
		}
		if(orders != null && !orders.isEmpty()) {
			List<javax.persistence.criteria.Order> list = new ArrayList<>();
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
