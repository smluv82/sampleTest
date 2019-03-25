package com.sample.test.config;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Configuration;

/**
 * 서킷 브레이커 활성화
 *
 * @author smluv82
 *
 */
@EnableCircuitBreaker
@Configuration
public class HystrixConfiguration {

}
