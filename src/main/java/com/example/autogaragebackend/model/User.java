package com.example.autogaragebackend.model;

import com.example.autogaragebackend.enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String username;

    @Column
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
