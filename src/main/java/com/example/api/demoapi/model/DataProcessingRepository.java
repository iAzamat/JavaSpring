package com.example.api.demoapi.model;

import com.example.api.demoapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataProcessingRepository extends JpaRepository<User, Long> {

}