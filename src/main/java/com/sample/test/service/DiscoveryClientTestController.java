package com.sample.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/discovery/test")
@RestController
public class DiscoveryClientTestController {
	private final DiscoveryClient discoveryClient;
	@Resource
	private RestTemplate ribbonRestTemplate;

	private final FeignClientTestInterface feignClientTestInterface;
	/**
	 * discovery client를 직접 호출하여 연결
	 * 문제점
	 * 1. 리본 클라이언트 측 부하 분산 장점 X, 서비스 호출의 책임이 클라이언트에 있음.
	 * 2. 소스가 길어지고 직접 URL 생성의 문제점.
	 *
	 * @return
	 */
	@GetMapping("/call")
	public String testDiscoveryClientCall() {
		try {
			RestTemplate rest = new RestTemplate();

			discoveryClient.getServices().forEach(service -> log.info("service : {}", service));
			List<ServiceInstance> instances = discoveryClient.getInstances("sampleboot2");

			ResponseEntity<String> result = rest.exchange(instances.get(0).getUri().toString() + "/redis/get/test-rkGQZ", HttpMethod.GET, null, String.class);
			return result.getBody();
		} catch(Exception e) {
			log.error("testCall Exception : ", e);
			return "fail";
		}
	}

	/**
	 * @LoadBalanced를 이용하여 RestTemplate을 생성한 ribbonRestTemplate bean을 이용.
	 *
	 * @return
	 */
	@GetMapping("/call2")
	public String testRibbonRestTemplateCall() {
		try {
			ResponseEntity<String> result = ribbonRestTemplate.exchange(String.format("http://%s/redis/get/test-rkGQZ", "sampleboot2"), HttpMethod.GET, null, String.class);
			return result.getBody();
		}catch(Exception e) {
			log.error("testCall2 Exception : ", e);
			return "fail";
		}
	}

	@GetMapping("/call3")
	public String testFeignClientCall() {
		try {
			return feignClientTestInterface.testCall();
		}catch(Exception e) {
			log.error("testCall3 Exception : ", e);
			return "fail";
		}
	}
}
