package ru.gb.homework9.reviewservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.homework9.reviewservice.database.repository.ReviewRepository;
import ru.gb.homework9.storeentity.entity.Review;
import ru.gb.homework9.storeutils.annotations.MyLog;
import ru.gb.homework9.storeutils.annotations.MyPerformance;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @MyLog
    @MyPerformance
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @MyLog
    @MyPerformance
    public Review findById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @MyLog
    @MyPerformance
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @MyLog
    @MyPerformance
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
