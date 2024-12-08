package com.practice.project;

import com.practice.project.service.sample.MyAsyncService;
import org.awaitility.Awaitility;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;


//@SpringBootTest
class MyAsyncServiceTest {

//    @Autowired
    private MyAsyncService myAsyncService;

//    @Test
    void testAsyncMethod() {
        // Call the asynchronous method and get CompletableFuture
        CompletableFuture<String> future = myAsyncService.asyncMethod();

        // Use Awaitility to wait until the CompletableFuture is completed
        Awaitility.await()
                .atMost(5, TimeUnit.SECONDS)  // Set a maximum wait time
                .until(future::isDone);  // Wait until the CompletableFuture is done

        // Assert that the returned result is what we expect
        assertEquals("Hello, Async!", future.join());
    }

//    @Test
    void testAsyncMethodWithException() {
        // Call the asynchronous method that throws an exception
        CompletableFuture<String> future = myAsyncService.asyncMethodWithException();

        // Wait until the future is completed or throws an exception
        Awaitility.await()
                .atMost(5, TimeUnit.SECONDS)
                .until(future::isDone);

        // Assert that the exception is thrown
        try {
            future.get();  // This will throw an ExecutionException if the CompletableFuture fails
        } catch (ExecutionException e) {
            assertInstanceOf(RuntimeException.class, e.getCause());
            assertTrue(e.getCause().getMessage().contains("Async exception occurred"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}