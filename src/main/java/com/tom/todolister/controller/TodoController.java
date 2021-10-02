package com.tom.todolister.controller;

import com.tom.todolister.model.TodoItem;
import com.tom.todolister.service.TodoListRepositoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoListRepositoryService service;

    @Operation(summary = "Get all todo-items", description = "Returns a list of all available todo-items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo-items found",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TodoItem.class)))}),
            @ApiResponse(responseCode = "404", description = "No todo-items found")
    })
    @GetMapping("/all")
    public ResponseEntity<List<TodoItem>> getAllTodoItems() {
        return new ResponseEntity<>(service.findAllTodoItems(), HttpStatus.OK);
    }

    @Operation(summary = "Get todo-items by id", description = "Searches database for a todo-items with a given id and returns it")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo-item found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TodoItem.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "No todo-items with the given id found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoItemById(
            @Parameter(description = "Id to be searched for") @PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Save a new todo-item to the database", description = "Saves a new todo-item to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo-item saved", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid todo-item supplied"),
    })
    @PostMapping("")
    public ResponseEntity<Void> addTodoItem(
            @Parameter(description = "Body of the new todo-item", schema = @Schema(implementation = TodoItem.class)) @RequestBody TodoItem newTodoItem) {
        service.create(newTodoItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update a todo-item", description = "Replace the values of a existing todo-item in the database with the new supplied ones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo-item updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid todo-item supplied"),
            @ApiResponse(responseCode = "404", description = "No todo-item with the supplied id found in the database")
    })
    @PutMapping("")
    public ResponseEntity<Void> updateTodoItem(
            @Parameter(description = "Body of the updated todo-item", schema = @Schema(implementation = TodoItem.class)) @RequestBody TodoItem updatedTodoItem) {
        service.update(updatedTodoItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete todo-item", description = "Delete the supplied todo-item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo-item deleted", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid todo-item supplied"),
            @ApiResponse(responseCode = "404", description = "No todo-item with the supplied id found in the database")
    })
    @DeleteMapping("")
    public ResponseEntity<Void> deleteTodoItem(
            @Parameter(description = "Body of the deleted todo-item", schema = @Schema(implementation = TodoItem.class)) @RequestBody TodoItem todoItemToDelete) {
        service.delete(todoItemToDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
