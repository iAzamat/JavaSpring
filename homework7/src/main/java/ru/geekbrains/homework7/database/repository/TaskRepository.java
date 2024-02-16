package ru.geekbrains.homework7.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.homework7.database.entity.Task;
import ru.geekbrains.homework7.database.entity.TaskStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByStatus(TaskStatus taskStatus);

    @Query("select t from Task t join fetch t.name where t.id=:id")
    Optional<Task> findWithJoinFetch(long id);

    @Query(value = "SELECT * " +
            "FROM employers_task " +
            "JOIN employer ON employers_task.employer_id = employer.id " +
            "JOIN task ON employers_task.task_id = task.id " +
            "WHERE employer.id = :employerId", nativeQuery = true)
    Optional<List<Object>> findEmployerTasksByEmployerId(@Param("employerId") Long employerId);
}
