package ru.geekbrains.homework7.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

public enum TaskStatus implements Serializable {
    TASK_NEW,
    TASK_IN_PROCESS,
    TASK_COMPETED;

    public static TaskStatus convert(String status) {
        if (status.toLowerCase().contains("new")) {
            return TASK_NEW;
        }
        if (status.toLowerCase().contains("proc")) {
            return TASK_IN_PROCESS;
        }
        if (status.toLowerCase().contains("comp")) {
            return TASK_COMPETED;
        }
        return null;
    }
}
