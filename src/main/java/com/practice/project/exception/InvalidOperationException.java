package com.practice.project.exception;

import lombok.experimental.StandardException;

// An exception class invokes when user input invalid choice for operation
@StandardException
public class InvalidOperationException extends RuntimeException {

}