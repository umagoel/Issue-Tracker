package com.newcreation.jira.issue;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class IssueType {
    @Id
    @Column(name = "ISSUE_TYPE_ID")
    @TableGenerator(name = "ISSUE_TYPE_ID", table = "ID_GENERATOR", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "ISSUE_TYPE_ID", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ISSUE_TYPE_ID")
    private Integer id;
    private String name;

}
