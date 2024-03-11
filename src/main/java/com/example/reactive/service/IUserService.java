package com.example.reactive.service;

import com.example.reactive.dto.UserDto;
import com.example.reactive.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {
    void create(UserDto e);

    Mono<User> findById(String id);

    Flux<User> findByName(String name);

    Flux<User> findAll();

    Mono<User> update(UserDto e);

    Mono<Void> delete(String id);
}
