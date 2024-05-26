package banas.tomasz.todolist.Service;

import banas.tomasz.todolist.Entity.TodoItem;
import banas.tomasz.todolist.Repository.TodoItemRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {

    private final TodoItemRepository todoItemRepository;

    @Autowired
    public TodoItemService(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    public List<TodoItem> findAll() {
        return todoItemRepository.findAll();
    }

    public Optional<TodoItem> findById(Long id) {
        return todoItemRepository.findById(id);
    }

    public TodoItem save(TodoItem todoItem) {
        return todoItemRepository.save(todoItem);
    }

    public boolean deleteById(Long id) {
        if (this.findById(id).isPresent()) {
            todoItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @PostConstruct
    public void initDatabase() {
        if (todoItemRepository.count() == 0) {
            TodoItem t = new TodoItem("JavaEE", "Zaliczenie JavaEE na 5.0", LocalDate.now(), false);
            TodoItem t0 = new TodoItem("HTML", "Technologie webowe i narzędzia programistyczne - Zaliczenie na 5.0", LocalDate.now(), false);
            TodoItem t1 = new TodoItem("Zadanie 1", "Opis zadania 1", LocalDate.now(), false);
            TodoItem t2 = new TodoItem("Zadanie 2", "Opis zadania 2", LocalDate.now().plusDays(1), true);
            TodoItem t3 = new TodoItem("Zadanie 3", "Opis zadania 3", LocalDate.now().plusDays(2), false);
            TodoItem t4 = new TodoItem("Zadanie nieukończone", "Zadanie nie zostało ukończone", LocalDate.now().minusDays(2), false);
            TodoItem t5 = new TodoItem("Zadanie 5", "Opis zadania 5", LocalDate.now().plusDays(10), false);

            todoItemRepository.saveAll(Arrays.asList(t,t0,t1, t2, t3,t4,t5));
        }

    }
}
