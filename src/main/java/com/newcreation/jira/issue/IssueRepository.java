package com.newcreation.jira.issue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, String> {

    List<Issue> findByAssigneeEmailId(String emailId);

}
