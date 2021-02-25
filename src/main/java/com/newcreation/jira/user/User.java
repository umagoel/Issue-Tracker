package com.newcreation.jira.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "EMAIL_ID", unique = true, nullable = false)
    private String emailId;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    @ManyToMany
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "EMAIL_ID", referencedColumnName = "EMAIL_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_NAME", referencedColumnName = "ROLE")})
    private List<Role> roles;
    @Transient
    private String fullName = firstName+lastName;

    public User(String emailId){
        this.emailId = emailId;
    }
    public String getFullName(){
        return this.firstName+" "+this.lastName;
    }
}
