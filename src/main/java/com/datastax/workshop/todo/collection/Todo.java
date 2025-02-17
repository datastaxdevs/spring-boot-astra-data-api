package com.datastax.workshop.todo.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @JsonProperty("_id")
    UUID uid;
    String title;
    Boolean completed = false;
    Integer offset = 0;
}
