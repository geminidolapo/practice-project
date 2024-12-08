package com.practice.project.service.eventdriven;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class WelcomeEmailListener {

//    @Async
    @EventListener
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        String userEmail = event.getUserEmail();
        // Logic to send a welcome email
        System.out.println("Sending welcome email to " + userEmail);
    }
}
