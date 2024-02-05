package com.example.api.demoapi.service;

import com.example.api.demoapi.model.DataProcessingRepository;
import com.example.api.demoapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataProcessingService {
    private final DataProcessingRepository dataProcessingRepository;
    private final NotificationService notificationService;

    @Autowired
    public DataProcessingService(DataProcessingRepository dataProcessingRepository, NotificationService notificationService) {
        this.dataProcessingRepository = dataProcessingRepository;
        this.notificationService = notificationService;
    }

    public List<User> getUsers() {
        return dataProcessingRepository.findAll();
    }

    public User addUser(User user) {
        boolean containsFlag = getUsers().stream().anyMatch(t -> t.equals(user));
        if (!containsFlag) {
            dataProcessingRepository.save(user);
            notificationService.notify(user + " was created");
        } else {
            notificationService.notify(user + " already in base");
        }
        return user;
    }

    public void setUsers(List<User> users) {
        dataProcessingRepository.saveAll(users);
    }

    public List<User> sortByName() {
        notificationService.notify("base was sorted by name");
        List<User> temp = getUsers().stream().sorted().toList();
        temp.stream().forEach(t -> notificationService.notify(t.toString()));
        return temp;
    }

    public List<User> filterByAge(int age) {
        notificationService.notify("filtered by age = " + age);
        List<User> temp = getUsers().stream().filter(t -> t.getAge() > age).toList();
        temp.stream().forEach(t -> notificationService.notify(t.toString()));
        return temp;
    }

    public double calculateAverageAge() {
        double temp = getUsers().stream().mapToInt(User::getAge).average().orElse(0.0);
        notificationService.notify("average age in base = " + temp);
        return temp;
    }
}
