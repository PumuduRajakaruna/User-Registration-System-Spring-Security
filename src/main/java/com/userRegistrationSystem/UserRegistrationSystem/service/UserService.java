package com.userRegistrationSystem.UserRegistrationSystem.service;


import com.userRegistrationSystem.UserRegistrationSystem.entity.User;
import com.userRegistrationSystem.UserRegistrationSystem.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);
}
