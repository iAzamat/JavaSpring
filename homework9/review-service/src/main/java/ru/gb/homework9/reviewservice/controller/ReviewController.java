package ru.gb.homework9.reviewservice.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.homework9.reviewservice.client.ExternalProductApi;
import ru.gb.homework9.reviewservice.service.ReviewService;
import ru.gb.homework9.storeentity.entity.Product;
import ru.gb.homework9.storeentity.entity.Review;

import java.util.List;

@Tag(name = "Review API Controller", description = "Review API Controller")
@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ExternalProductApi productApi;

    @GetMapping("/about")
    public String about() {
        return "reviewservice";
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public Review findById(@PathVariable("id") Long id) {
        return reviewService.findById(id);
    }

    @PostMapping
    public Review addReview(@RequestBody Review review) {
        Product product = productApi.findProductById(review.getProductId());

        if (product != null) {
            Review temp = Review.builder()
                    .content(review.getContent() + " " + product.getName())
                    .rating(review.getRating())
                    .author(review.getAuthor())
                    .productId(review.getProductId())
                    .build();

            return reviewService.addReview(temp);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}
