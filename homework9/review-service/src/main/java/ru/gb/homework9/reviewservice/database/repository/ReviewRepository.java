package ru.gb.homework9.reviewservice.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.homework9.reviewservice.database.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
