package com.ds.user_service.service;

import com.ds.user_service.entities.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUserById(String userId);

    List<User> getAllUser();

}
