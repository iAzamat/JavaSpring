package ru.gb.homework12.model.factory;

import ru.gb.homework12.database.entity.Task;
import ru.gb.homework12.database.entity.UrgentTask;

public class UrgentTaskFactory implements ITaskFactory {
    @Override
    public Task createTask() {
        return new UrgentTask();
    }
}
