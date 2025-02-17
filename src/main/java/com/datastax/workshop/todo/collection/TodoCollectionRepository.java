package com.datastax.workshop.todo.collection;

import com.datastax.astra.client.collections.Collection;
import com.datastax.astra.client.collections.CollectionOptions;
import com.datastax.astra.client.collections.commands.results.CollectionInsertOneResult;
import com.datastax.astra.client.collections.definition.CollectionDefaultIdTypes;
import com.datastax.astra.client.collections.definition.CollectionDefinition;
import com.datastax.astra.client.core.query.Filters;
import com.datastax.astra.client.databases.Database;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.datastax.astra.client.core.query.Filters.eq;

@Repository
public class TodoCollectionRepository {

    public static final String COLLECTION_NAME = "todos_collection";

    @Autowired
    private Database db;

    private Collection<Todo> collection;

    @PostConstruct
    private void init() {
        collection = db.createCollection(COLLECTION_NAME,
                new CollectionDefinition().defaultId(CollectionDefaultIdTypes.UUID),
                Todo.class);
    }

    public List<Todo> findAll() {
        return collection.findAll().toList();
    }

    public Optional<Todo> findById(UUID id) {
        return collection.findById(id);
    }

    public void create(Todo todo) {
        collection.insertOne(todo);
    }

    public void update(Todo todo) {
        collection.findOneAndReplace(eq("_id", todo.getUid()), todo);
    }

    public boolean existsById(UUID id) {
        return collection.findOne(eq("_id", id)).isPresent();
    }

    public void deleteById(UUID todoId) {
        collection.deleteOne(Filters.eq("_id", todoId));
    }
}
