package org.james.demo.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.james.demo.model.User;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

	public final Map<String, User> data = new ConcurrentHashMap<>();
	
	{
		data.put("1", new User("1", "a"));
		data.put("2", new User("2", "b"));
		
	}

	public Flux<User> list() {
		return Flux.fromIterable(this.data.values());
	}

	public Flux<User> getById(Flux<String> ids) {
		return ids.flatMap(id -> Mono.justOrEmpty(this.data.get(id)));
	}

	public Mono<User> getById(String id) {
		return Mono.justOrEmpty(this.data.get(id)).switchIfEmpty(Mono.error(new Exception("Can't find User.")));
	}

	public Flux<User> createOrUpdate(Flux<User> users) {
		return users.doOnNext(user -> this.data.put(user.getUserId(), user));
	}
	
	public Mono<User> createOrUpdate(User user) {
		this.data.put(user.getUserId(), user);
		
		return Mono.just(user);
	}

	public Mono<User> delete(String id) {
		return Mono.justOrEmpty(this.data.remove(id));
	}
}
