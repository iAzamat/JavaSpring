package ru.gb.homework12.model.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.homework12.database.entity.RoutineTask;
import ru.gb.homework12.database.entity.Task;
import ru.gb.homework12.database.entity.UrgentTask;
import ru.gb.homework12.model.TaskType;
import ru.gb.homework12.service.TaskService;

@Component
@RequiredArgsConstructor
public class TaskFacade {
    private final TaskService taskService;

    public Task createTask(String name, String description, String status, TaskType type) {
        if (type == TaskType.ROUTINE) {
            Task task = new RoutineTask
                    .TaskBuilder()
                    .name(name)
                    .description(description)
                    .status(status)
                    .build();
            return taskService.createTask(task, type);
        }
        if (type == TaskType.URGENT) {
            Task task = new UrgentTask
                    .TaskBuilder()
                    .name(name)
                    .description(description)
                    .status(status)
                    .build();
            return taskService.createTask(task, type);
        }
        return null;
    }
}
