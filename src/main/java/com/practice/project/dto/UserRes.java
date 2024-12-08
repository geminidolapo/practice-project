package com.practice.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.practice.project.dao.primary.entity.Role;
import lombok.Data;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
//@ToString
public class UserRes {

//    @JsonAlias({"userName", "user_name","username"})
    private String username;
//    @JsonAlias({"firstName", "firstname","first_name"})
    private String firstName;
//    @JsonAlias({"lastName", "lastname","last_name"})
    private String lastName;

    private String email;

    private boolean active;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedOn;

    @JsonIgnore
    @ToString.Exclude
    private Set<Role> roles;

    private Set<String> assignedRoles;

    public Set<String> getAssignedRoles() {
        if (assignedRoles == null && roles != null) {
            assignedRoles = new HashSet<>();
            roles.forEach(userRole -> assignedRoles.add(userRole.getName()));
        }
        return assignedRoles != null ? assignedRoles : new HashSet<>();
    }
}

