package com.newcreation.jira.issue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueTypeRepository extends JpaRepository<IssueType,Integer> {
}
