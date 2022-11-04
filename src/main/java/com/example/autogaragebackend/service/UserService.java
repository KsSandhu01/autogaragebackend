package com.example.autogaragebackend.service;

import com.example.autogaragebackend.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public interface UserService {

    public abstract long createUser(User user);
    public abstract void updateUser(long id, User user);
    public abstract void partialUpdateUser(long id, Map<String, String> fields);
    public abstract void deleteUser(long id);
    public abstract Collection<User> getUsers();
    public abstract Collection<User> getUsersByName(String name);
    public abstract Collection<User> getUsersByRole(String role);
    public abstract Optional<User> getUserById(long id);
    public abstract boolean userExistsById(long id);
}
