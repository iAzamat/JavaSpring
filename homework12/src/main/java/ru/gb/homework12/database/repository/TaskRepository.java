package ru.gb.homework12.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.homework12.database.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
