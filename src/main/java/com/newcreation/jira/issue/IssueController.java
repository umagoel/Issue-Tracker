package com.newcreation.jira.issue;

import com.newcreation.jira.common.SecurityUtils;
import com.newcreation.jira.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issue")
public class IssueController {
    @Autowired
    private IssueService issueService;
//    @Autowired
//    private SecurityUtils securityUtils;
    @GetMapping("/all")
    public List<Issue> getAllForUser(){
        return issueService.getAll(SecurityUtils.getCurrentLoggedIn());
    }
    @PostMapping
    public Issue save(@RequestBody Issue issue){
        issue.setReporter(new User(SecurityUtils.getCurrentLoggedIn()));
        return issueService.save(issue);
    }

    @PutMapping("/issue-type")
    public IssueType addIssueType(@RequestBody IssueType issueType){
        return issueService.save(issueType);
    }

    @PutMapping("/issue-status")
    public IssueStatus addIssueType(@RequestBody IssueStatus issueStatus){
        return issueService.save(issueStatus);

    }

    @GetMapping("/issue-type/all")
    public List<IssueType> getAll(){
        return issueService.getAll();
    }

    @GetMapping("issue-status/all")
    public List<IssueStatus> getAllIssueTypes(){
        return issueService.getAllIssueType();
    }



}
