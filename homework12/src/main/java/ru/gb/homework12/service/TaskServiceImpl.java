package ru.gb.homework12.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.homework12.database.entity.RoutineTask;
import ru.gb.homework12.database.entity.Task;
import ru.gb.homework12.database.repository.TaskRepository;
import ru.gb.homework12.model.TaskType;
import ru.gb.homework12.model.factory.RoutineTaskFactory;
import ru.gb.homework12.model.factory.UrgentTaskFactory;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    private static volatile TaskServiceImpl instance;

    public static TaskServiceImpl getInstance() {
        TaskServiceImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (TaskServiceImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new TaskServiceImpl();
                }
            }
        }

        return localInstance;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(Task task, TaskType type) {
        Task tempTask = null;
        if (TaskType.ROUTINE == type) {
            tempTask = new RoutineTaskFactory().createTask();
            tempTask.setName(task.getName());
            tempTask.setDescription(task.getDescription());
            tempTask.setStatus(task.getStatus());
            tempTask.execute();
            taskRepository.save(tempTask);
        }
        if (TaskType.URGENT == type) {
            tempTask = new UrgentTaskFactory().createTask();
            tempTask.setName(task.getName());
            tempTask.setDescription(task.getDescription());
            tempTask.setStatus(task.getStatus());
            tempTask.execute();
            taskRepository.save(tempTask);
        }
        return tempTask;
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task updateTask(Long id, Task taskNew) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setName(taskNew.getName());
            task.setDescription(task.getDescription());
            task.setStatus(task.getStatus());
            task.execute();
            taskRepository.save(task);
        }

        return task;
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}