package com.tom.todolister.repository;

import com.tom.todolister.model.PriorityLevel;
import com.tom.todolister.model.TodoItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouchbaseRepository extends CrudRepository<TodoItem, String> {
    List<TodoItem> findByTitle(String title);
    List<TodoItem> findByPriorityLevel(PriorityLevel priorityLevel);
}
