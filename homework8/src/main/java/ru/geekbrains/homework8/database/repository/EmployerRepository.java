package ru.geekbrains.homework8.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.homework8.database.entity.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {
}
