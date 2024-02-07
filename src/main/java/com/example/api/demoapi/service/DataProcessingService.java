package com.example.api.demoapi.service;

import com.example.api.demoapi.model.DataProcessingRepository;
import com.example.api.demoapi.model.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return dataProcessingRepository.findAll(sort);
    }

    public User addUser(User user) {
        boolean containsFlag = getUsers().stream().anyMatch(t -> t.equals(user));
        if (!containsFlag) {
            dataProcessingRepository.save(user);
            notificationService.notify(user + " register in base");
        } else {
            notificationService.notify(user + " already in base");
        }
        return user;
    }

    @NotNull
    public boolean findUser(long id) {
        return dataProcessingRepository.findById(id).isPresent();
    }

    public User updateUser(long id, String name, int age, String email) {
        User user = dataProcessingRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(name);
            user.setAge(age);
            user.setEmail(email);
            dataProcessingRepository.save(user);
        }
        return user;
    }


    public void deleteUser(long id) {
        notificationService.notify(dataProcessingRepository.getReferenceById(id) + " was deleted");
        dataProcessingRepository.deleteById(id);
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
