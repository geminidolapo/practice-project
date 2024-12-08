package com.practice.project.service.factorydesignpattern;

public class AddOperation implements Operation {
    @Override
    public double calculate(double number1, double number2) {
        return number1 + number2;
    }
}