package ru.geekbrains.homework5.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

import static ru.geekbrains.homework5.Model.TaskStatus.TASK_NEW;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 255, nullable = false)
    private String description;

    @Column(nullable = false)
    private TaskStatus status;

    @Column(nullable = false)
    private Date dateBegin;

    @Column(nullable = true)
    private Date dateEnd;

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
