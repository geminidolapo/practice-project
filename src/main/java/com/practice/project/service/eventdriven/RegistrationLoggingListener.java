package com.practice.project.service.eventdriven;


import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationLoggingListener {

    @EventListener
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        String userEmail = event.getUserEmail();
        // Logic to log the event
        System.out.println("User registered with email " + userEmail);
    }
}
