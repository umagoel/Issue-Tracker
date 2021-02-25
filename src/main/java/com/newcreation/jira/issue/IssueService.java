package com.newcreation.jira.issue;

import com.newcreation.jira.user.User;
import com.newcreation.jira.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private IssueTypeRepository issueTypeRepository;
    @Autowired
    private IssueStatusRepository issueStatusRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Issue> getAll(String emailId){
        return issueRepository.findByAssigneeEmailId(emailId);
    }
    @Transactional
    public Issue save(Issue issue) {
        issue= issueRepository.save(issue);
        issue.setKey(issue.getProject().getKey()+"-"+issue.getId());
        return issue;
    }

    public IssueType save(IssueType issueType) {
        return issueTypeRepository.save(issueType);
    }

    public IssueStatus save(IssueStatus issueStatus) {
        return issueStatusRepository.save(issueStatus);
    }
    public List<IssueType> getAll(){
        return issueTypeRepository.findAll();
    }

    public List<IssueStatus> getAllIssueType() {
        return  issueStatusRepository.findAll();
    }

//    public List<User> getAllReporters() {
//        return userRepository.findAll();
//    }
}
