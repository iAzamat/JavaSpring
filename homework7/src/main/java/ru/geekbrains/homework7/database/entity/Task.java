package ru.geekbrains.homework7.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

import static ru.geekbrains.homework7.database.entity.TaskStatus.TASK_NEW;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String description;
    private TaskStatus status;
    private Date dateBegin;
    private Date dateEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employer employer;

    public Task(String description) {
        this.description = description;
        this.status = TASK_NEW;
        this.dateBegin = new Date();
        this.dateEnd = null;
    }

    public Task() {
        this.description = "";
        this.status = TASK_NEW;
        this.dateBegin = new Date();
        this.dateEnd = null;
    }
}
