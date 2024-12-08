package com.practice.project.controller;

import com.practice.project.constant.StatusEnum;
import com.practice.project.dto.ResultResponse;
import com.practice.project.exception.BadRequestException;
import com.practice.project.exception.GenericException;
import com.practice.project.util.HelperClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.format.DateTimeParseException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResultResponse<ProblemDetail>> handleDateTimeParseException(DateTimeParseException ex) {
        log.error("DateTimeParseException occurred: {}", ex.getMessage(), ex);

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Invalid Date Format");

        var response = ResultResponse.error(StatusEnum.PARAM_INVALID, problemDetail);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultResponse<ProblemDetail>> handleException(Exception ex) {
        log.error("Unhandled exception occurred: {}", ex.getMessage(), ex);

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        problemDetail.setTitle("Internal Server Error");
        var response = ResultResponse.error(StatusEnum.SERVICE_ERROR, problemDetail);

        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResultResponse<ProblemDetail>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error("data integrity exception occurred: {}", ex.getMessage(), ex);

        String processedMessage = HelperClass.extractMessageBeforeFirstFor(ex.getLocalizedMessage());
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, processedMessage);
        problemDetail.setTitle("Processing Error");

        var response = ResultResponse.error(StatusEnum.PARAM_INVALID, problemDetail);
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ResultResponse<ProblemDetail>> handleAccessDeniedException(AuthorizationDeniedException ex) {
        log.warn("access denied exception occurred: {}", ex.getMessage(), ex);

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        problemDetail.setTitle("Access denied Error");

        var response = ResultResponse.error(StatusEnum.FORBIDDEN, problemDetail);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ResultResponse<ProblemDetail>> handleGenericException(GenericException ex) {
        log.error("Generic exception occurred: {}", ex.getMessage(), ex);

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        problemDetail.setTitle("Generic Error");

        var response = ResultResponse.error(StatusEnum.SERVICE_ERROR, problemDetail);
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResultResponse<ProblemDetail>> handleBadRequestException(BadRequestException ex) {
        log.error("BadRequestException occurred: {}", ex.getMessage(), ex);

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Input Error");

        var response = ResultResponse.error(StatusEnum.PARAM_INVALID, problemDetail);
        return ResponseEntity.badRequest().body(response);
    }
}
