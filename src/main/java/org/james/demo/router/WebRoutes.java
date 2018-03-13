package org.james.demo.router;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.james.demo.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Configuration
public class WebRoutes {

	@Autowired
	private UserHandler userHandler;

	@Bean
	public RouterFunction<ServerResponse> helloRoute() {
		return route(GET("/"), request -> {
						Mono<String> str = Mono.just("Welcome to Spring Webflux.");
						return ok().body(fromPublisher(str, String.class));})
				.andRoute(GET("/hello/{name}"), userHandler::hello);
	}

}
