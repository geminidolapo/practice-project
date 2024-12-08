package com.practice.project.service.sample;

@FunctionalInterface
public interface Functionalinterface {
    void test(); // Single abstract method

    default void defaultMethod() {
        // ...
    }

    static void staticMethod() {
        // ...
    }
}
