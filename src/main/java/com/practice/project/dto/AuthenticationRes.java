package com.practice.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@AllArgsConstructor
@FieldNameConstants
//@FieldNameConstants(asEnum = true)
//@FieldNameConstants(prefix = "FIELD_")
//@FieldNameConstants(onlyExplicitlyIncluded = true)
public class AuthenticationRes {
//    @FieldNameConstants.Include
    private String accessToken;
    private UserRes user;
}