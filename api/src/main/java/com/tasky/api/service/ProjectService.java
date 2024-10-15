package com.tasky.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tasky.api.entity.Project;
import com.tasky.api.repository.ProjectRepository;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository p) {
        this.projectRepository = p;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project savProject(Project p) {
        return projectRepository.save(p);
    }

    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }
}
