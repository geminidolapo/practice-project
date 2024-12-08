package com.practice.project.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.practice.project.constant.ValidPassword;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.Set;

@Data
public class RegisterUserReq {

    @Pattern(regexp = "^[A-Za-z]+$", message = "Field must contain only alphabetic characters")
    @NotBlank(message = "username cannot be null or empty")
    @JsonAlias({"userName", "user_name","username"})
    private String username;

    @ValidPassword(message = "password cannot contain more than 2 digits or special characters")
    @Size(min = 7, message = "password cannot be less than 7 characters")
    @NotBlank(message = "password cannot be null or empty")
    @NotBlank(message = "password cannot be null or empty")
    private String password;

    @NotBlank(message = "firstName cannot be null or empty")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Field must contain only alphabetic characters")
    private String firstname;

    @NotBlank(message = "lastName cannot be null or empty")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Field must contain only alphabetic characters")
    private String lastname;

    @Email(message = "invalid email format")
    @NotBlank(message = "email cannot be null or empty")
    private String email;

    @NotEmpty(message = "must pass at least one role" )
    @NotNull(message = "role cannot be null")
    private Set<String> roles;
}
