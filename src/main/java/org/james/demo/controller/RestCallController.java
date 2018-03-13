package org.james.demo.controller;

import org.james.demo.service.RestCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class RestCallController {
	
	@Autowired
	private RestCallService restCallService;
	
	@GetMapping("/restcall/{name}")
	public Mono<String> restCall(@PathVariable String name) {		
		return restCallService.someRestCall(name);
	}

}
