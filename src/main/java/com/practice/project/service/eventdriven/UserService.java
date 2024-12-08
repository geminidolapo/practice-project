package com.practice.project.service.eventdriven;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final ApplicationEventPublisher eventPublisher;

    public UserService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void registerUser(String email) {
        // Logic to save the user to the database
//        userRepository.save(user);

        // Publish the event
        eventPublisher.publishEvent(new UserRegisteredEvent(this, email));
    }
}
