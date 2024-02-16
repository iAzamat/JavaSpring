package ru.geekbrains.homework7.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

public enum TaskStatus implements Serializable {
    TASK_NEW,
    TASK_IN_PROCESS,
    TASK_DONE;

    public static TaskStatus convert(String status) {
        if (status.toLowerCase().contains("new")) {
            return TASK_NEW;
        }
        if (status.toLowerCase().contains("proc")) {
            return TASK_IN_PROCESS;
        }
        if (status.toLowerCase().contains("done")) {
            return TASK_DONE;
        }
        return null;
    }
}
