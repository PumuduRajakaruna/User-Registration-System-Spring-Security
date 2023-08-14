package com.userRegistrationSystem.UserRegistrationSystem.service;

import com.userRegistrationSystem.UserRegistrationSystem.entity.User;
import com.userRegistrationSystem.UserRegistrationSystem.entity.VerificationToken;
import com.userRegistrationSystem.UserRegistrationSystem.model.UserModel;
import com.userRegistrationSystem.UserRegistrationSystem.repository.UserRepository;
import com.userRegistrationSystem.UserRegistrationSystem.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        userRepository.save(user);
        return user;
    }

    @Override
    public void SaveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken = new
                VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }
}
