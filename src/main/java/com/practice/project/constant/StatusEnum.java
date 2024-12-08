package com.practice.project.constant;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusEnum {
    SUCCESS(200, "Request processed successfully"),
    UNAUTHORIZED(401, "User authentication failed"),
    FORBIDDEN(403, "Insufficient permissions"),
    SERVICE_ERROR(500, "Server is on a trip, please try again later"),
    PARAM_INVALID(1000, "Invalid parameter");

    public final Integer code;
    public final String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
