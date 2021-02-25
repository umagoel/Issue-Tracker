package com.newcreation.jira.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @GetMapping("/all")
    public List<Project> getAllProjects(){
        System.out.println(projectService.getAllProjects());
        return projectService.getAllProjects();
    }
    @PostMapping("/save")
    public Project save(@RequestBody Project project){
        return projectService.save(project);
    }

    @GetMapping("/category/all")
    public List<Category> getAllCategories(){
        return projectService.getAllCategories();
    }

}
