package org.james.demo.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class UserHandler {
	
	public Mono<ServerResponse> hello(ServerRequest serverRequest) {
		String name = serverRequest.pathVariable("name");
		 
		System.out.println("hello " + name);
		
		if(true) {
			throw new RuntimeException("Test Exception");
		}
		
		return status(HttpStatus.OK).contentType(MediaType.TEXT_EVENT_STREAM)
				.body(Mono.just("Hello " + name), String.class);
		
	}

}
