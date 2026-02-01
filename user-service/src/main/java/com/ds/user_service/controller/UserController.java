package com.ds.user_service.controller;

import com.ds.user_service.entities.User;
import com.ds.user_service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<@NonNull User> saveUser(@RequestBody User user){

        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        User saveUser = userService.createUser(user);

        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    int retryCount=1;
    @GetMapping("/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    //@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<@NonNull User> saveUser(@PathVariable String userId){

        log.info("Get Single User Handler: UserController");
        log.info("Retry count: {}",retryCount);
        retryCount++;

        User user = userService.getUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    public ResponseEntity<@NonNull User> ratingHotelFallback(String userId, Exception exception){
       //log.info("Fallback is executed because service is down : ", exception.getMessage());

        User user = User.builder().email("dummy@gmail.com")
                .userName("Dummy")
                .about("This is created dummy user, because some services are down")
                .userId("6234625348")
                .build();
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<@NonNull List<User>> saveUser(){

        List<User> users = userService.getAllUser();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
