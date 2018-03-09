package org.james.demo.router;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.james.demo.handler.UserHandler;
import org.james.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Configuration
public class WebRoutes {

	@Autowired
	private UserHandler userHandler;

	@Bean
	public RouterFunction<ServerResponse> route() {
		return RouterFunctions.route(GET("/"), request -> {
			Mono<User> user = Mono.just(new User("1", "a"));
			return ok().body(fromPublisher(user, User.class));
		}).andRoute(GET("/hello/{name}"), userHandler::hello);
	}

}
