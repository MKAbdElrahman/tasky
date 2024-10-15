package com.tasky.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasky.api.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
