package com.practice.project.service.sample;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class MyAsyncService {

    @Async
    public CompletableFuture<String> asyncMethod() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Simulating a long-running task
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, Async!";
        });
    }

    @Async
    public CompletableFuture<String> asyncMethodWithException() {
        return CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Async exception occurred");
        });
    }
}