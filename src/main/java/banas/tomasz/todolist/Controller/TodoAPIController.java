package banas.tomasz.todolist.Controller;


import banas.tomasz.todolist.Entity.TodoItem;
import banas.tomasz.todolist.Service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoAPIController {

    @Autowired
    private TodoItemService todoService;

    // Pobieranie wszystkich zadań
    @GetMapping
    public List<TodoItem> getAllTodos() {
        return todoService.findAll();
    }

    // Pobieranie zadania po ID
    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoById(@PathVariable Long id) {

        return todoService.findById(id)
                .map(todoItem -> ResponseEntity.ok().body(todoItem))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tworzenie nowego zadania
    @PostMapping("/set")
    public TodoItem createTodo(@RequestBody TodoItem todoItem) {
        return todoService.save(todoItem);
    }

    // Aktualizacja istniejącego zadania
    @PutMapping("/update/{id}")
    public ResponseEntity<TodoItem> updateTodo(@PathVariable Long id, @RequestBody TodoItem todoItemDetails) {
        return todoService.findById(id)
                .map(todoItem -> {
                    todoItem.setTitle(todoItemDetails.getTitle());
                    todoItem.setDescription(todoItemDetails.getDescription());
                    todoItem.setDueDate(todoItemDetails.getDueDate());
                    todoItem.setCompleted(todoItemDetails.isCompleted());
                    TodoItem updatedTodo = todoService.save(todoItem);
                    return ResponseEntity.ok().body(updatedTodo);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Usuwanie zadania
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id) {
        boolean status = todoService.deleteById(id);
        if (status) {
            return ResponseEntity.ok().build();
        }
       return ResponseEntity.notFound().build();
    }
}
