package com.example.reactive.service;

import com.example.reactive.dto.UserDto;
import com.example.reactive.entity.User;
import com.example.reactive.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void create(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user).subscribe();
    }

    @Override
    public Mono<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Flux<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> update(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return userRepository.save(user);
    }

    @Override
    public Mono<Void> delete(String id) {
        return userRepository.deleteById(id);
    }
}
