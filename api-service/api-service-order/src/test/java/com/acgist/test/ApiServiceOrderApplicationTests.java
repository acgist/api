package com.acgist.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.acgist.main.ApiServiceOrderApplication;
import com.acgist.order.pojo.entity.OrderEntity;
import com.acgist.order.repository.OrderRepository;
import com.acgist.pojo.select.Filter;
import com.acgist.pojo.select.Order;
import com.acgist.pojo.select.PageQuery;
import com.acgist.pojo.select.PageResult;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiServiceOrderApplication.class)
public class ApiServiceOrderApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private OrderRepository orderRepository;
	
	@Test
	public void findOne() {
//		orderRepository.deleteById("402881e46690d069016690d0a5140000");
		List<OrderEntity> value = orderRepository.findList(2, 2, Order.desc("orderId"), Filter.like("id", "%1%"));
		value.forEach(order -> {
			System.out.println("orderId：" + order.getOrderId());
		});
//		List<OrderEntity> value = orderRepository.findAll(Filter.like("id", "%1%"));
//		value.forEach(order -> {
//			System.out.println("orderId：" + order.getOrderId());
//		});
//		OrderEntity value = orderRepository.findOne(Filter.like("id", "%1%"));
//		System.out.println("orderId：" + value.getOrderId());
	}
	
	@Test
	public void findPage() {
		PageQuery pageQuery = new PageQuery(0, 2);
		pageQuery.addOrders(Order.desc("id"));
		pageQuery.addFilters(Filter.like("id", "%1%"));
		PageResult<OrderEntity> pages = null;
		pages = orderRepository.findPage(pageQuery);
		pages.getResult().forEach(order -> {
			System.out.println("orderId：" + order.getOrderId());
		});
		System.out.println("查询总量：" + pages.getResult().size());
		System.out.println("数据总量：" + pages.getTotal());
		System.out.println("每页数量：" + pages.getPageSize());
		System.out.println("总共页数：" + pages.getTotalPage());
	}
	
}
