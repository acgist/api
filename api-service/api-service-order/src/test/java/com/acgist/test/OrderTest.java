package com.acgist.test;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.acgist.order.api.request.PayRequest;

public class OrderTest {

	@Test
	public void order() throws InterruptedException {
		RestTemplate rest = new RestTemplate();
		int count = 1000;
		long begin = System.currentTimeMillis();
		rest.setErrorHandler(new ResponseErrorHandler() {
			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				return false;
			}
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
			}
		});
		CountDownLatch latch = new CountDownLatch(count);
		ExecutorService exe = Executors.newFixedThreadPool(100);
		for (int i = 0; i < count; i++) {
			exe.submit(() -> {
				PayRequest request = new PayRequest();
				request.setOrderId("fail");
//				request.setOrderId("exception");
//				ResponseEntity<String> response = rest.getForEntity(URI.create("http://192.168.1.100:34010"), String.class);
//				zuul网关
				ResponseEntity<String> response = rest.postForEntity(URI.create("http://192.168.1.100:23010/gateway/api/order"), request, String.class);
//				网关服务
//				ResponseEntity<String> response = rest.postForEntity(URI.create("http://192.168.1.100:32010/gateway/api/order"), request, String.class);
//				直接调用服务
//				ResponseEntity<String> response = rest.postForEntity(URI.create("http://192.168.1.100:36010/service/order"), request, String.class);
				System.out.println(response.getBody());
				System.out.println(response.getStatusCodeValue());
				System.out.println(response.getHeaders().getContentType());
				latch.countDown();
			});
		}
		latch.await();
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
		exe.shutdown();
	}
	
}
