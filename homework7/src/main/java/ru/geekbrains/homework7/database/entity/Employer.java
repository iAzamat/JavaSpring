package ru.geekbrains.homework7.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "employerTasks")
@EqualsAndHashCode(of = "firstname")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employer implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String firstname;

    public Employer(String firstname) {
        this.firstname = firstname;
    }


    @ManyToMany()
    @JsonBackReference
    @JoinTable(
            name = "employers_task",
            joinColumns = @JoinColumn(name = "employer_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Task> employerTasks = new ArrayList<>();

    public void addTask(Task task) {
        if (!employerTasks.contains(task)) {
            employerTasks.add(task);
            task.getEmployers().add(this);
        }
    }

    public void removeTask(Task task) {
        if (employerTasks.contains(task)) {
            employerTasks.remove(task);
            task.getEmployers().remove(this);
        }
    }

    public void updateTask(Task oldTask, Task newTask) {
        if (employerTasks.contains(oldTask)) {
            int index = employerTasks.indexOf(oldTask);
            employerTasks.set(index, newTask);
            newTask.getEmployers().add(this);
            oldTask.getEmployers().remove(this);
        }
    }
}
