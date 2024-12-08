package com.practice.project.service.factorydesignpattern;

import com.practice.project.exception.InvalidOperationException;

public interface OperationFactory {
    Operation getInstance(int choice) throws InvalidOperationException;
}
