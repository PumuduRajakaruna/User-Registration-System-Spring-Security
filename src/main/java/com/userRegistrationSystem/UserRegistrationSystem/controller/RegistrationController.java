package com.userRegistrationSystem.UserRegistrationSystem.controller;

import com.userRegistrationSystem.UserRegistrationSystem.entity.User;
import com.userRegistrationSystem.UserRegistrationSystem.event.RegistrationCompleteEvent;
import com.userRegistrationSystem.UserRegistrationSystem.model.UserModel;
import com.userRegistrationSystem.UserRegistrationSystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher publisher;
    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationUrl(request)
        ));
        return "Success";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")) {
            return "User Verified Successfully";
        }
        return "Bad User";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" +
                request.getServerPort() + request.getContextPath();
    }
}
