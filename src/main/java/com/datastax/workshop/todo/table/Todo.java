package com.datastax.workshop.todo.table;

import com.datastax.astra.client.tables.mapping.EntityTable;
import com.datastax.astra.client.tables.mapping.PartitionBy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityTable("todos")
public class Todo {
    @PartitionBy(0)
    UUID    uid;
    String  title;
    Boolean completed = false;
    Integer offset = 0;
}
