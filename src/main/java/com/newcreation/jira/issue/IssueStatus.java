package com.newcreation.jira.issue;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="ISSUE_STATUS")
public class IssueStatus {
    @Id
    @Column(name="ISSUE_STATUS_ID" )
    @TableGenerator(name = "ISSUE_STATUS_ID", table = "ID_GENERATOR", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "ISSUE_STATUS_ID", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ISSUE_STATUS_ID")
    private Integer id;
    private String name;
    private Integer level;
}
