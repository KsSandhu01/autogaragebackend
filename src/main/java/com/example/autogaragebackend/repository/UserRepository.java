package com.example.autogaragebackend.repository;

import com.example.autogaragebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long> {

    public Collection<User> findAllByName(String name);
    public Collection<User> findAllByRole(String role);
}
