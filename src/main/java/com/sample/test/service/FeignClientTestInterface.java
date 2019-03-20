package com.sample.test.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * FeignClient에 호출 할 서비스 ID 입력
 *
 * @author smluv82
 *
 */
@FeignClient(name="sampleboot2")
public interface FeignClientTestInterface {
	@GetMapping("/redis/get/test-rkGQZ")
	String testCall();
}
