package com.practice.project.service.sample;

public class MyClass {
    public static void main(String[] args) {
        // We are asking the ClassLoader to find and load the class named "MyClass"
        ClassLoader classLoader = MyClass.class.getClassLoader();
        try {
            Class<?> aClass = classLoader.loadClass("MyClass");
            System.out.println("Class Loaded: " + aClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
