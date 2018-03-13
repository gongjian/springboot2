package org.james.demo.service;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
@Validated
public class RestCallService {

	@Autowired
	private WebClient.Builder webClientBuilder;

	/*
	 * private final WebClient webClient;
	 * 
	 * public RestCallService(WebClient.Builder webClientBuilder) { this.webClient =
	 * webClientBuilder.baseUrl("http://127.0.0.1:8080").build(); }
	 */

	public Mono<String> someRestCall(@Size(min = 5, max = 10) String name) {
		System.out.println("Rest Call.....");

		// return this.webClient.get().uri("/hello/{name}",
		// name).retrieve().bodyToMono(String.class);
		return webClientBuilder.build().get().uri("/hello/{name}", name).retrieve().bodyToMono(String.class);
	}

}
