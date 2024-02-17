package ru.geekbrains.homework8.database.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ru.geekbrains.homework8.database.entity.TaskStatus.TASK_NEW;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = false, nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDate dateBegin;
    private LocalDate dateEnd;


    @ManyToMany(mappedBy = "employerTasks")
    @JsonManagedReference
    private List<Employer> employers = new ArrayList<>();

    public Task(String name) {
        this.name = name;
        this.description = null;
        this.status = TASK_NEW;
        this.dateBegin = LocalDate.now();
        this.dateEnd = null;
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = TASK_NEW;
        this.dateBegin = LocalDate.now();
        this.dateEnd = null;
    }

    public void addEmployer(Employer employer) {
        if (!employers.contains(employer)) {
            employers.add(employer);
            employer.getEmployerTasks().add(this);
        }
    }

    public void removeEmployer(Employer employer) {
        if (employers.contains(employer)) {
            employers.remove(employer);
            employer.getEmployerTasks().remove(this);
        }
    }

    public void updateEmployer(Employer oldEmployer, Employer newEmployer) {
        if (employers.contains(oldEmployer)) {
            int index = employers.indexOf(oldEmployer);
            employers.set(index, newEmployer);
            newEmployer.getEmployerTasks().add(this);
            oldEmployer.getEmployerTasks().remove(this);
        }
    }
}
