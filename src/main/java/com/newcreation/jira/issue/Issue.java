package com.newcreation.jira.issue;

import com.newcreation.jira.project.Project;
import com.newcreation.jira.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Entity
@Getter
@Setter
@Table(name="ISSUE")
public class Issue {
    @Id
    @TableGenerator(name = "ISSUE_ID", table = "ID_GENERATOR", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "ISSUE_ID", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ISSUE_ID")
    @Column(name = "ISSUE_ID")
    private Integer id;
    @Column(name = "ISSUE_KEY")
    private String key;
    @ManyToOne()
    @JoinColumn(name = "ISSUE_STATUS", referencedColumnName = "ISSUE_STATUS_ID")
    private IssueStatus issueStatus;
    @ManyToOne
    @JoinColumn(name="ISSUE_TYPE" , referencedColumnName = "ISSUE_TYPE_ID")
    private IssueType issueType;
    private String title;
    @Column(name = "DESCRIPTION")
    private String desc;
    @ManyToOne
    private Project project;
    @ManyToOne
    @JoinColumn(name = "ASSIGNEE")
    private User assignee;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    private String fixVersion;
    @ManyToOne
    @JoinColumn(name = "EMAIL_ID")
    private User reporter;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ISSUE_WATCHERS",
            joinColumns = {@JoinColumn(name = "ISSUE_KEY")},
            inverseJoinColumns = {@JoinColumn(name = "EMAIL_ID")})
    private List<User> watchers;
    @Column(name = "ENVIRONMENT")
    private String environment;
    @Column(name="DUE_DATE")
    private LocalDate dueDate;

}
