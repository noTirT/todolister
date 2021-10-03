package com.tom.todolister.service;

import com.tom.todolister.model.TodoItem;
import com.tom.todolister.repository.CouchbaseRepository;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Qualifier("TodoListRepositoryService")
@RequiredArgsConstructor
public class TodoListRepositoryService implements TodoService{

    private final CouchbaseRepository repository;

    public TodoItem findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<TodoItem> findAllTodoItems() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<TodoItem> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public void create(TodoItem todoItem) {
        todoItem.setCreated(DateTime.now());
        repository.save(todoItem);
    }

    public void update(TodoItem todoItem) {
        todoItem.setUpdated(DateTime.now());
        repository.save(todoItem);
    }

    public void delete(TodoItem todoItem) {
        repository.delete(todoItem);
    }
}
