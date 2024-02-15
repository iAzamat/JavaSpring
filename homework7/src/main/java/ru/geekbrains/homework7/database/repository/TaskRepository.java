package ru.geekbrains.homework7.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.homework7.database.entity.Task;
import ru.geekbrains.homework7.database.entity.TaskStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByStatus(TaskStatus taskStatus);

    @Query("select t from Task t join fetch t.employer where t.id=:id")
    Optional<Task> findWithJoinFetch(long id);

    Optional<List<Task>> findTasksByEmployer_Id(long id);
}
