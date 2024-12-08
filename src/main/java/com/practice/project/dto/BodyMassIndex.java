package com.practice.project.dto;

public record BodyMassIndex(double height, double weight, int age) {
    public Double calculate(){
        return height()*weight()*age();
    }
}
