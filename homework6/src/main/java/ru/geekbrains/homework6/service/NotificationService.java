package ru.geekbrains.homework6.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {
    public void notify(String msg) {
        System.out.println(LocalDateTime.now() + " : " + msg);
    }
}
