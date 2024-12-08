package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:4200")  // Autoriser les requêtes venant de localhost:4200
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Endpoint POST pour créer une tâche
    
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        System.out.println("Received Task: " + task);  // Vérifiez ce que vous obtenez ici

        if (task == null || task.getTitle() == null || task.getTitle().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Task savedTask = taskService.saveTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Optional<Task> task = taskService.findTaskById(id);
        if (!task.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // Mise à jour d'une tâche
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Optional<Task> task = taskService.findTaskById(id);
        if (!task.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Task currentTask = task.get();
        currentTask.setTitle(updatedTask.getTitle());
        currentTask.setDescription(updatedTask.getDescription());
        currentTask.setCompleted(updatedTask.getCompleted());
        currentTask.setStatus(updatedTask.getStatus());
        
        Task savedTask = taskService.saveTask(currentTask);
        return ResponseEntity.ok(savedTask);
    }



    // Endpoint GET pour récupérer toutes les tâches
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    
}
