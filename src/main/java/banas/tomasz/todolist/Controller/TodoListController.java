package banas.tomasz.todolist.Controller;

import banas.tomasz.todolist.Entity.TodoItem;
import banas.tomasz.todolist.Service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TodoListController {
    @Autowired
    private TodoItemService todoService;

    @GetMapping("/")
    public String showTodoList() {

        return "index";
    }
}
