package com.tom.todolister.service;

import com.tom.todolister.model.PriorityLevel;
import com.tom.todolister.model.TodoItem;

import java.util.List;

public interface TodoService {

    TodoItem findById(String id);
    List<TodoItem> findAllTodoItems();
    List<TodoItem> findByTitle(String title);
    List<TodoItem> findByPriorityLevel(PriorityLevel priorityLevel);

    void create(TodoItem todoItem);
    void update(TodoItem todoItem);
    void delete(TodoItem todoItem);

}
