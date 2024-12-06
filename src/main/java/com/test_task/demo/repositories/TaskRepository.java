package com.test_task.demo.repositories;

import com.test_task.demo.entities.Task;
import com.test_task.demo.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

 //@Repository
//public interface TaskRepository extends JpaRepository<Task, Long> {
//
//    Page<Task> findByAuthor(User author, Pageable pageable);
//
//    Page<Task> findByAssignee(User assignee, Pageable pageable);
//
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByAuthor_IdOrAssignee_IdAndStatusAndPriority(
            Long authorId, Long assigneeId, String status, String priority, Pageable pageable);
    }

    // Additional custom methods for status, priority, etc.
    // Example:
    // List<Task> findByStatusAndPriority(TaskStatus status, TaskPriority priority, Pageable pageable);
