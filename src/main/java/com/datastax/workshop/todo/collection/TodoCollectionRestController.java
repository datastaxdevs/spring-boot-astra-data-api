package com.datastax.workshop.todo.collection;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequestMapping("/collections/todos")
public class TodoCollectionRestController {
    
    private TodoCollectionRepository repo;
    
    public TodoCollectionRestController(TodoCollectionRepository collectionRepository) {
        this.repo = collectionRepository;
    }
    
    @GetMapping
    public Stream<Todo> findAll() {
        return repo.findAll().stream();
    }
    
    @GetMapping("/{uid}")
    public ResponseEntity<?> findById(@PathVariable(value = "uid") UUID uid) {
        return repo
                .findById(uid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
     
    @PostMapping
    public ResponseEntity<Todo> create(HttpServletRequest req, @RequestBody Todo todoReq)
    throws URISyntaxException {
        if (todoReq.getUid() == null) {
            todoReq.setUid(UUID.randomUUID());
        }
        repo.create(todoReq);
        return ResponseEntity
                .created(new URI(req.getRequestURI() + "/" + todoReq.getUid()))
                .body(todoReq);
    }

    @PutMapping("/{uid}")
    public ResponseEntity<Todo> update(@PathVariable String uid, @RequestBody Todo todo) {
        UUID todoId = UUID.fromString(uid);
        if (!repo.existsById(todoId)) {
            return ResponseEntity.notFound().build();
        }
        todo.setUid(todoId);
        repo.update(todo);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("{uid}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "uid") String uid) {
        UUID todoId = UUID.fromString(uid);
        if (!repo.existsById(todoId)) return ResponseEntity.notFound().build();
        repo.deleteById(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
