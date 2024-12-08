package com.practice.project.service.retryable;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class Retry {
    @Retryable(value =Exception.class,  exclude = IllegalArgumentException.class)
//            condition = "#{#root.args[0] != 'no-Retry'}"
    public String fetchRemoteData(String controlFlag) {
        // Network call that might fail
        return "Data";
    }

    @Retryable(value = { Exception.class, TimeoutException.class }, maxAttempts = 5 , backoff = @Backoff(delay = 2000) ,include = TimeoutException.class)
//    @Retryable(value = TimeoutException.class, backoff = @Backoff(delay = 2000))
//    @Retryable(value = Exception.class, backoff = @Backoff(delay = 1000, multiplier = 2))
    //@Retryable(stateful = true)
    //@Retryable(listeners = "myRetryListenerBean")
    public String fetchRemoteData() {
        // Network call that might fail
        return "Data";
    }

    @Recover
    public void recover(SQLException e) {
        // Recovery logic when retries are exhausted
        System.out.println("Recovering from SQLException: " + e.getMessage());
    }

    @Recover
    public String recover(Exception e) {
        // Fallback logic
        return "Default Data";
    }
}
