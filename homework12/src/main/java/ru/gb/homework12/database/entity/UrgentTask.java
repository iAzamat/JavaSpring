package ru.gb.homework12.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UrgentTask extends Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String status;

    @Override
    public void execute() {
        System.out.println("Выполняется срочная задача");
    }

    public static class TaskBuilder {
        private final Task task = new UrgentTask();

        public TaskBuilder name(String name) {
            task.setName(name);
            return this;
        }

        public TaskBuilder description(String description) {
            task.setDescription(description);
            return this;
        }

        public TaskBuilder status(String status) {
            task.setStatus(status);
            return this;
        }

        public Task build() {
            return task;
        }
    }
}
