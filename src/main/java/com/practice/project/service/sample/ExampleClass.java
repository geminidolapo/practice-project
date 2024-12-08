package com.practice.project.service.sample;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleClass {

    private final ScopedValue<String> SAMPLE = ScopedValue.newInstance();
    // Alternative to Thread Local
    public void doWork() {
        // Enter a ScopedValue binding
        ScopedValue.where(SAMPLE, "SampleValue").run(() -> {
            // Inside this scope, SAMPLE has the value "SampleValue"
            System.out.println("Scoped Value: " + SAMPLE.get());
            // Perform operations within this scope
        });

        // Outside the scope, SAMPLE does not have a value
        System.out.println("Outside Scoped Value");
    }


    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private int value1 = 0;
    private int value2 = 0;


    // using synchronized lock instead of having volatile fields
    public void incrementValue1() {
        synchronized (lock1) {
            value1++;
            log.info("Value 1 incremented to: {}", value1);
        }
    }

    public void decrementValue2() {
        synchronized (lock2) {
            value2--;
            log.info("Value 2 decremented to: {}", value2);
        }
    }
}
