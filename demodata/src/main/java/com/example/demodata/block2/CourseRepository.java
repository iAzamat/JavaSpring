package com.example.demodata.block2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Student, Long> {}
