package com.userRegistrationSystem.UserRegistrationSystem.service;


import com.userRegistrationSystem.UserRegistrationSystem.entity.User;
import com.userRegistrationSystem.UserRegistrationSystem.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {
    User registerUser(UserModel userModel);

    void SaveVerificationTokenForUser(String token, User user);
}
