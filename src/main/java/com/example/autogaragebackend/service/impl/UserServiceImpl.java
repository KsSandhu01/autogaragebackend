package com.example.autogaragebackend.service.impl;

import com.example.autogaragebackend.exception.ResourceNotFoundException;
import com.example.autogaragebackend.model.User;
import com.example.autogaragebackend.repository.UserRepository;
import com.example.autogaragebackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public long createUser(User user) {
        user.setPassword(getEncryptedPassword(user.getPassword()));
        User newUser = userRepository.save(user);
        return newUser.getId();
    }


    @Override
    public void updateUser(long id, User user) {
        if (!userRepository.existsById(id)) throw new ResourceNotFoundException();
        User exitingUser = userRepository.findById(id).get();
        exitingUser.setName(user.getName());
        exitingUser.setUsername(user.getUsername());
        exitingUser.setPassword(user.getPassword());
        user.setPassword(getEncryptedPassword(user.getPassword()));
        exitingUser.setRole(user.getRole());
        userRepository.save(exitingUser);
    }

    @Override
    public void partialUpdateUser(long id, Map<String, String> fields) {
        if (!userRepository.existsById(id)) throw new ResourceNotFoundException();
        User user = userRepository.findById(id).get();
        for (String field : fields.keySet()) {
            switch (field.toLowerCase()) {
                case "name":
                    user.setName((String) fields.get(field));
                    break;
                case "username":
                    user.setUsername((String) fields.get(field));
                    break;
                case "password":
                    user.setPassword((String) fields.get(field));
                    break;
            }
        }
        user.setPassword(getEncryptedPassword(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        if (!userRepository.existsById(id)) throw new ResourceNotFoundException();
        userRepository.deleteById(id);
    }

    @Override
    public Collection<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Collection<User> getUsersByName(String name) {
        return userRepository.findAllByName(name);
    }

    @Override
    public Collection<User> getUsersByRole(String role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public Optional<User> getUserById(long id) {
        if (!userRepository.existsById(id)) throw new ResourceNotFoundException();
        return userRepository.findById(id);
    }

    @Override
    public boolean userExistsById(long id) {
        return userRepository.existsById(id);
    }


    private String getEncryptedPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
