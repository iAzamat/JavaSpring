package com.example.api.demoapi.model;

import java.util.Objects;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User implements Comparable<Object> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, length = 60)
    private String name;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false, length = 60)
    private String email;

    public User() {
        this.name = "";
        this.age = 0;
        this.email = "";
    }

    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;

        if (age != user.age) return false;
        if (!Objects.equals(name, user.name)) return false;
        return Objects.equals(email, user.email);
    }

    @Override
    public int compareTo(Object object) {
        return name.compareTo(((User) (object)).getName());
    }

}
