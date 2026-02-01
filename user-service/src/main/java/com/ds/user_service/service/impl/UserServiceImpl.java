package com.ds.user_service.service.impl;

import com.ds.user_service.entities.Hotel;
import com.ds.user_service.entities.Rating;
import com.ds.user_service.entities.User;
import com.ds.user_service.exception.ResourceNotFoundException;
import com.ds.user_service.external.services.HotelService;
import com.ds.user_service.repositories.UserRepository;
import com.ds.user_service.service.UserService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Override
    public User createUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
        Rating[] ratingArray = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        assert ratingArray != null;
        List<Rating> ratings = Arrays.asList(ratingArray);
        log.info(String.valueOf(ratings));

        List<Rating> ratingList = ratings.stream().map((rating -> {
            //ResponseEntity<@NonNull Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            //Hotel hotel = forEntity.getBody();
             //       log.info(String.valueOf(forEntity.getStatusCode()));
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
           rating.setHotel(hotel);
           return rating;
        })).toList();
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();
    }
}
