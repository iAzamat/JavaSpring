package ru.gb.homework10.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    private final int sessionTime = 45;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long userId;

    @PrePersist
    public void prePersist() {
        this.startDate = LocalDateTime.now();
        this.endDate = this.startDate.plusMinutes(sessionTime);
    }
}
