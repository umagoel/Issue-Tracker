package com.newcreation.jira.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Role {
    @Id
    @Column(name = "ROLE", unique = true, nullable = false)
    private String role;
}
