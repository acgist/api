package com.api.test;

import java.io.IOException;
import java.net.URI;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.api.core.gateway.APICode;
import com.api.core.order.gateway.request.PayRequest;
import com.api.core.service.SignService;
import com.api.utils.JSONUtils;

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
				try {
					PayRequest request = new PayRequest();
					request.setUsername("test");
					request.setOrderId("fail");
					request.setRequestTime("1234");
					Map<String, String> data= request.data();
					PublicKey publicKey = SignService.stringToPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCtP/NhtJmUWQguBGMHG3VOC1ETDiAZrj+xe0Qpr7zndGtnGLngG31bSqXArHcMikYB5OvgRSA3gu8dZ+jw92STCf2tMI+nnMdKnYwfQvRj+TT3W/I2VGX7xa6NGVf2otTgR+D7fBFj/XIasfaYO4SA1KlEGH1Qb2gR8gclY4ff/wIDAQAB");
					PrivateKey privateKey = SignService.stringToPrivateKey("MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAK0/82G0mZRZCC4EYwcbdU4LURMOIBmuP7F7RCmvvOd0a2cYueAbfVtKpcCsdwyKRgHk6+BFIDeC7x1n6PD3ZJMJ/a0wj6ecx0qdjB9C9GP5NPdb8jZUZfvFro0ZV/ai1OBH4Pt8EWP9chqx9pg7hIDUqUQYfVBvaBHyByVjh9//AgMBAAECgYEAnVtZbqP/Xjtjb5BydXEeIY2rzLfmtgyLM2O3bHl/BWa7an+NzByLxlBNrmu3EX/ByFSpXSDL+z+EeVv4umypPlgnodO4DVweaG4AOnV/QqAAVvv/0LkhKdJPK0a5481ZiWldGschi91sCO6ulbkZmbeaqafcK/7ILWeJBd0b+xECQQDl9+fPBv9j3S83IM9GrLx7qHv5efXt72NJ4uUeJhpCvFCGSJ9TaTsvxoNq9wBuwYNUhVMfW3NA8DxmWtPsl605AkEAwNxv2wDyn7p1AgvaELw8zBR3X/sAyMljjpTCZHIHjszchSV6jQrxSAR1mGm6ggoow81I/CtZAgPi2DpxNAuu9wJBAM4vOeg1hu97lWShBxZ/szJPum/QOH5PHr9VLkWctfjVrIDpRehcNhO8sYFK2llzKROzwfYgYTJ88jp2/wFmcIkCQDmzumOz7OONTeTG1v+a0jXdCbpVxu5MEtVd/KtA0rIO6QuNIxV/6KC7dNOdDohJsdijWurXrVHFB+HZ2nTP6hECQQDSoiEaoBU4owh7cIcSooYRv6gHnNsRNPmbgfOA757PJwWkieUKaNZ1V7ZN++rA0bn5CWickZ15QZW7SY3ekpw1");
					String sign = SignService.sign(SignService.dataToDigest(data), privateKey);
					request.setSign(sign);
//					request.setOrderId("exception");
//					zuul网关
					ResponseEntity<String> response = rest.postForEntity(URI.create("http://192.168.1.100:23010/gateway/api/order/pay"), request, String.class);
//					服务网关
//					ResponseEntity<String> response = rest.postForEntity(URI.create("http://192.168.1.100:32010/gateway/api/order/pay"), request, String.class);
//					直接调用服务
//					ResponseEntity<String> response = rest.postForEntity(URI.create("http://192.168.1.100:32010/service/order"), request, String.class);
					String json = response.getBody();
					Map<String, String> returnData = JSONUtils.jsonToMap(json)
						.entrySet()
						.stream()
						.map(entry -> {
							return Map.entry(entry.getKey(), (String) entry.getValue());
						})
						.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
					String verifyString = SignService.dataToDigest(returnData);
					String signString = returnData.get("sign");
					if(SignService.verify(verifyString, signString, publicKey)) {
//						System.out.println("ok");
						if(!APICode.success(returnData.get("responseCode"))) {
							System.out.println(json);
							System.out.println("交易失败");
						}
					} else {
						System.out.println("验签失败");
					}
//					System.out.println(json);
//					System.out.println(response.getStatusCodeValue());
//					System.out.println(response.getHeaders().getContentType());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					latch.countDown();
				}
			});
		}
		latch.await();
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
		exe.shutdown();
	}
	
}
