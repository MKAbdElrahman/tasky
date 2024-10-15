package com.tasky.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasky.api.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
