package com.tasky.api.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasky.api.entity.Project;
import com.tasky.api.entity.Task;
import com.tasky.api.service.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService s) {
        projectService = s;
    }

    @GetMapping
    public List<Project> gettAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@RequestParam Long id) {
        Optional<Project> p = projectService.getProjectById(id);

        if (p.isPresent()) {
            return ResponseEntity.ok(p.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public Project saveProject(@RequestBody Project project) {
        return projectService.savProject(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {
        Optional<Project> p = projectService.getProjectById(id);
        if (p.isPresent()) {
            Project currentProject = p.get();
            currentProject.setName(project.getName());
            currentProject.setDescription(project.getDescription());
            currentProject.getTasks().clear();

            for (Task t : project.getTasks()) {
                currentProject.addTask(t);
            }
            return ResponseEntity.ok(projectService.savProject(currentProject));

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Long id) {
        projectService.deleteProjectById(id);
        return ResponseEntity.noContent().build();
    }

}
