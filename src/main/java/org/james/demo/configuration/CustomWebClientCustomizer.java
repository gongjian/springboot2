package org.james.demo.configuration;

import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient.Builder;

@Configuration
public class CustomWebClientCustomizer implements WebClientCustomizer {

	@Override
	public void customize(Builder webClientBuilder) {
		webClientBuilder.baseUrl("http://127.0.0.1:8080");
	}

}
