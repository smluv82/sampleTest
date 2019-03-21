package com.sample.test.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 다른 서비스 호출 방법은 총 3가지.
 *
 * 1. EnableDiscoveryClient Annotation을 사용하면,
 * DiscoveryClient로 직접 호출할 수 있게 된다.
 *
 * 2.LoadBalanced Annotation을 사용하여, restTemplate이
 * 스프링 클라우드가 지원하는 리본을 사용
 *
 * 3. 넷플릭스의 EnableFeignClients Annotation을 사용하여,
 * Feign인터페이스를 정의하여 서비스 호출하는 방법
 * @author smluv82
 *
 */
//@EnableFeignClients
@EnableDiscoveryClient
@Configuration
public class DiscoveryClientConfiguration {
	@Bean(name="ribbonRestTemplate")
	@LoadBalanced
	public RestTemplate restTemplate() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setConnectTimeout(5000);
		factory.setReadTimeout(5000);
		RestTemplate restTemplate =  new RestTemplate(factory);
		return restTemplate;
//		return new RestTemplate();
	}

}
