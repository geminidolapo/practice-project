package com.practice.project.service.sample;

public class Clipboard {
    private String value;

    private static Clipboard clipboard = null;
    private static volatile Clipboard instance;

    // Private constructor to prevent instantiation from outside
    private  Clipboard() {}

    // Method to provide access to the singleton instance
    public static Clipboard getInstance() {
        if (clipboard == null) {
            clipboard = new Clipboard();
        }
        return clipboard;
    }


    public static Clipboard getSingletonInstance() {
        if (instance == null) {
            synchronized (Clipboard.class) {
                if (instance == null) {
                    instance = new Clipboard();
                }
            }
        }
        return instance;
    }


    public void copy(String value) {
        this.value = value;
    }

    public String paste() {
        return value;
    }
}