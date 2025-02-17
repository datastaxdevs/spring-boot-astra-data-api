package com.datastax.workshop.todo.table;

import com.datastax.astra.client.collections.Collection;
import com.datastax.astra.client.core.query.Filters;
import com.datastax.astra.client.databases.Database;
import com.datastax.astra.client.tables.Table;
import com.datastax.workshop.todo.table.Todo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.datastax.astra.client.core.query.Filters.eq;

@Repository
public class TodoTableRepository {

    @Autowired
    private Database db;

    private Table<Todo> table;

    @PostConstruct
    private void init() {
        table = db.createTable(Todo.class);
    }

    public List<Todo> findAll() {
        return table.findAll().toList();
    }

    public Optional<Todo> findById(UUID id) {
        return table.findOne(eq("_id", id));
    }

    public void save(Todo todo) {
        table.insertOne(todo);
    }

    public boolean existsById(UUID id) {
        return findById(id).isPresent();
    }

    public void deleteById(UUID todoId) {
        table.deleteOne(Filters.eq("_id", todoId));
    }
}
