package com.newcreation.jira.project;

import com.newcreation.jira.issue.Issue;
import com.newcreation.jira.issue.IssueStatus;
import com.newcreation.jira.issue.IssueType;
import com.newcreation.jira.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="PROJECT")
public class Project {
    @Id
    @TableGenerator(name = "PROJECT_ID", table = "ID_GENERATOR", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "PROJECT_ID", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PROJECT_ID")
    @Column(name = "PROJECT_ID")
    private Integer id;
    @Column(name = "PROJECT_KEY", nullable = false, unique = true)
    private String key;
    @Column(name = "PROJECT_NAME", unique = true, nullable = false)
    private String projectName;
    @Column(name = "PROJECT_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectType projectType;
    @ManyToOne
    @JoinColumn(name = "PROJECT_LEAD")
    private User projectLead;
    @ManyToOne
    @JoinColumn(name = "PROJECT_CATEGORY", referencedColumnName = "CATEGORY_ID")
//    @JoinTable(
//            name = "PROJECT_CATEGORY",
//            joinColumns = {@JoinColumn(name = "PROJECT_ID" , referencedColumnName = "PROJECT_ID")},
//            inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID" , referencedColumnName = "CATEGORY_ID")})
    private Category projectCategory;
    private String url;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "PROJECT_ISSUE_STATUS",
            joinColumns = {@JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ISSUE_STATUS_ID", referencedColumnName = "ISSUE_STATUS_ID")})
    private List<IssueStatus> issueStatusList;
    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "PROJECT_ISSUE_TYPE" , referencedColumnName = "ISSUE_TYPE_ID" )
    @JoinTable(
            name = "PROJECT_ISSUE_TYPE",
            joinColumns = {@JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ISSUE_TYPE_ID", referencedColumnName = "ISSUE_TYPE_ID")})
    private List<IssueType> issueTypes;


}
