package com.tom.todolister.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class TodoItem {

    @Schema(description = "Id of the todo-item")
    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    @Field
    private String id;

    @Schema(description = "Title of the todo-item", example = "Clean desk")
    @Field
    @NotNull
    private String title;

    @Schema(description = "Priority-level of the todo-item", implementation = PriorityLevel.class)
    @Field
    @NotNull
    private PriorityLevel priorityLevel;

    @Schema(description = "Description of the todo-item", example = "Remove unnecessary items from the desk and wipe it down")
    @Field
    @NotNull
    private String description;

    @Schema(description = "Date when the todo-item was created")
    @Field
    @NotNull
    private DateTime created;

    @Schema(description = "Date when the todo-item was updated the last time")
    @Field
    private DateTime updated;

}
