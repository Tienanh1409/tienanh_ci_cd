package com.example.reactive.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import com.example.reactive.dto.UserDto;
import com.example.reactive.entity.User;
import com.example.reactive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"user/create/", "/"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserDto userDto) {
        userService.create(userDto);
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Mono<User>> findById(@PathVariable("id") String id) {
        Mono<User> userMono = userService.findById(id);
        return new ResponseEntity<Mono<User>>(userMono, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> findAll() {
        Flux<User> users = userService.findAll();
        log.info("Da chay");
        return users;
    }

    @RequestMapping(value = {"/callme"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void callMe() {
        for (int i = 1; i <= 100; i++) {
            UserDto userDto = new UserDto();
            userDto.setId(String.valueOf(i));
            userDto.setName(String.valueOf(i + 1000));
            userService.create(userDto);
        }
    }
}
