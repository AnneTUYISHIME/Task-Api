package org.example.apimanagementtask.task;



import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * Get all tasks from the database
     */
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    /**
     * Get a single task by its ID
     */
    public Task getTaskById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    /**
     * Create a new task
     * Make sure ID is null, so Hibernate generates it
     */
    public Task createTask(Task task) {
        task.setId(null); // Important: ensures new task
        return repository.save(task);
    }

    /**
     * Update an existing task by ID
     * Only updates fields of the loaded entity
     */
    public Task updateTask(Long id, Task updatedTask) {
        return repository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setDueDate(updatedTask.getDueDate());
            task.setStatus(updatedTask.getStatus());
            return repository.save(task); // save the loaded entity
        }).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

        /**
         * Delete a task by its ID
         */
        void deleteTask(Long id) {
            if (!repository.existsById(id)) {
                throw new RuntimeException("Task not found with id " + id);
            }
            repository.deleteById(id);
        }
    }


