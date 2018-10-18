package com.acgist.test;

import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.acgist.api.request.order.OrderRequest;

public class OrderTest {

	@Test
	public void order() throws InterruptedException {
		RestTemplate rest = new RestTemplate();
		int count = 200;
		long begin = System.currentTimeMillis();
		CountDownLatch latch = new CountDownLatch(count);
		ExecutorService exe = Executors.newFixedThreadPool(100);
		for (int i = 0; i < count; i++) {
			exe.submit(() -> {
				ResponseEntity<String> response = rest.postForEntity(URI.create("http://192.168.1.100:31010/gateway/api/order"), new OrderRequest(), String.class);
				System.out.println(response.getStatusCodeValue());
				System.out.println(response.getBody());
				latch.countDown();
			});
		}
		latch.await();
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
		exe.shutdown();
	}
	
}
