package com.userRegistrationSystem.UserRegistrationSystem.listner;

import com.userRegistrationSystem.UserRegistrationSystem.entity.User;
import com.userRegistrationSystem.UserRegistrationSystem.event.RegistrationCompleteEvent;
import com.userRegistrationSystem.UserRegistrationSystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.util.UUID;
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private UserService userService;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

        //create the verification token for the user with link
         User user = event.getUser();
         String token = UUID.randomUUID().toString();
         userService.SaveVerificationTokenForUser(token, user);

         //Send mail to user
        String url = event.getApplicationUrl() + "verifyRegistration?token=" + token;

         //sendVerificationEmail()
        log.info("Click the link to verify your account: {}", url);
    }
}
