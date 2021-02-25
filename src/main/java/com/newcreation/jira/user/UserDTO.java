package com.newcreation.jira.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class UserDTO {
    private String emailId;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private List<Role> roles;

    public UserDTO(User user) {
        this.emailId = user.getEmailId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.roles = user.getRoles();
    }
}
