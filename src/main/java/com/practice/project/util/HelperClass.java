package com.practice.project.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HelperClass {
    // Helper method to extract message before "for"
    public String extractMessageBeforeFirstFor(String message) {
        int index = message.indexOf(" for ");
        if (index != -1) {
            return message.substring(0, index).trim();
        }
        return message;
    }
}
