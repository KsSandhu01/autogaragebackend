package com.example.autogaragebackend.controller;

import com.example.autogaragebackend.model.User;
import com.example.autogaragebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public ResponseEntity<Collection<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping(value = "")
    public ResponseEntity<URI> createUser(@RequestBody User user) {
        long newId = userService.createUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateUserPartial(@PathVariable("id") long id, @RequestBody Map<String, String> fields) {
        userService.partialUpdateUser(id, fields);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
